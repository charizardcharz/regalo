package net.arcatanium.regalo.controller;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class RegaloWebController {

    WishlistService wishlistService;

    @Autowired
    public RegaloWebController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlists")
    public String getAllWishlists (Model model) {
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        model.addAttribute("wishlists", wishlists);
        return "wishlists";
    }

    @GetMapping("/wishlist/{wishlistId}")
    public String getWishlistsById (@PathVariable String wishlistId, Model model) {
        Optional<Wishlist> wishlistOptional = wishlistService.getWishlistById(wishlistId);
        if (wishlistOptional.isPresent()){
            model.addAttribute("wishlist", wishlistOptional.get());
        }
        return "wishlist";
    }
}
