package com.cartservice.service.impl;

import com.cartservice.VO.CartDetail_Product;
import com.cartservice.VO.Product;
import com.cartservice.common.Constants;
import com.cartservice.entity.CartDetail;
import com.cartservice.repository.CartDetailRepository;
import com.cartservice.service.CartDetailService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartDetailServiceImpl implements  CartDetailService {

    @Autowired
    private CartDetailRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    @Retry(name = "basic")
    @Override
    public CartDetail_Product addCartDetailProduct(CartDetail cartDetail) {

        List<CartDetail> list= repository.findByCartId(cartDetail.getCart().getId());

        for(int i =0; i< list.size();i++ ){
            if(list.get(i).getProductId()==cartDetail.getProductId()){
                list.get(i).setAmount(list.get(i).getAmount()+cartDetail.getAmount());
                CartDetail  crtDetail= repository.save(list.get(i));
                Product product=restTemplate.getForObject(Constants.PRODUCT+"/"+crtDetail.getProductId(), Product.class);
                return new CartDetail_Product(crtDetail,product);
            }
        }
        CartDetail crtDetail= repository.save(cartDetail);
        Product product=restTemplate.getForObject(Constants.PRODUCT+"/"+crtDetail.getProductId(), Product.class);
        return new CartDetail_Product(crtDetail,product);
    }
    @Retry(name = "basic")
    @Override
    public CartDetail_Product updateCartDetailProduct(CartDetail cartDetail) {
        CartDetail crtDetail= repository.save(cartDetail);
        Product product=restTemplate.getForObject(Constants.PRODUCT+"/"+crtDetail.getProductId(), Product.class);
        return new CartDetail_Product(crtDetail,product);
    }


    @Override
    public String deleteCartDetailProduct(List<Long> ids) {
         repository.deleteAllById(ids);
         return "Xóa thành công";
    }

    @Retry(name = "basic",fallbackMethod = "getFallback")
    @Override
    public CartDetail_Product findById(Long id) {
        CartDetail cartDetail= repository.findById(id).get();
        Product product=restTemplate.getForObject(Constants.PRODUCT+"/"+cartDetail.getProductId(),Product.class);
        return new CartDetail_Product(cartDetail,product);
    }
    public CartDetail_Product getFallback(Long id, RuntimeException runtimeException) {
      CartDetail cartDetail= repository.findById(id).get();
        CartDetail_Product cp= new CartDetail_Product();
       cp.setCartDetail(cartDetail);
        return cp;
    }
    @Retry(name = "basic",fallbackMethod = "getFallback2")
    @Override
    public List<CartDetail_Product> findByCartId(Long id) {
        List<CartDetail_Product> list = new ArrayList<CartDetail_Product>();
        List<CartDetail> listCartDetails=repository.findByCartId(id);
        for (CartDetail cartDetail: listCartDetails) {
            Product product=restTemplate.getForObject(Constants.PRODUCT+"/"+cartDetail.getProductId(),Product.class);
            list.add(new CartDetail_Product(cartDetail,product));
        }
    return list;
    }


    public List<CartDetail_Product> getFallback2(Long id, RuntimeException runtimeException) {
        List<CartDetail_Product> list = new ArrayList<CartDetail_Product>();
        List<CartDetail> listCartDetails=repository.findByCartId(id);
        for (CartDetail cartDetail: listCartDetails) {
            CartDetail_Product cp= new CartDetail_Product();
            cp.setCartDetail(cartDetail);
            list.add(cp);
        }
        return list;
    }

    @Override
    public String deleteCartDetailByCartId(Long id) {
        List<CartDetail> listCartDetails=repository.findByCartId(id);
        repository.deleteAll(listCartDetails);
        return "Xóa thành công";
    }


}
