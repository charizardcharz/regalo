package net.arcatanium.regalo.controller;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class RegaloWebController {
    private static final String WISHLIST_ATTRIBUTE_NAME = "wishlist";

    WishlistService wishlistService;

    @Autowired
    public RegaloWebController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlists")
    public String getAllWishlists (Model model) {
        List<Wishlist> wishlists = wishlistService.getAllWishlists();
        model.addAttribute("wishlists", wishlists);
        return "all-wishlists";
    }

    @GetMapping("/wishlist/display")
    public String getWishlistsById (@RequestParam(name="id") String wishlistId, Model model) {
        Optional<Wishlist> wishlistOptional = wishlistService.getWishlistById(wishlistId);
        wishlistOptional.ifPresent(wishlist -> model.addAttribute(WISHLIST_ATTRIBUTE_NAME, wishlist));
        return "display-wishlist";
    }

    @GetMapping("/wishlist/edit")
    public String editWishlistByIdForm(@RequestParam(name="id") String wishlistId, Model model) {
        Optional<Wishlist> wishlistOptional = wishlistService.getWishlistById(wishlistId);
        wishlistOptional.ifPresent(wishlist -> model.addAttribute(WISHLIST_ATTRIBUTE_NAME, wishlist));
        return "edit-wishlist";
    }

    @GetMapping("/wishlist/new")
    public String editWishlistByIdForm(Model model) {
        WishlistEntity wishlistEntity = wishlistService.createNewWishlist();
        model.addAttribute(WISHLIST_ATTRIBUTE_NAME, Wishlist.convertFromEntity(wishlistEntity));
        return "redirect:display?id=" + wishlistEntity.getWishlistId().toString();
    }

    @PostMapping("/wishlist/edit")
    public String editWishlistByIdSubmit(@ModelAttribute Wishlist wishlist, Model model) {
        Optional<WishlistEntity> wishlistEntityOptional = wishlistService.saveWishlist(wishlist);
        wishlistEntityOptional.ifPresent(wishlistEntity ->
                model.addAttribute(WISHLIST_ATTRIBUTE_NAME, Wishlist.convertFromEntity(wishlistEntity)));
        return "edit-wishlist";
    }

    @GetMapping("/wishlist/delete")
    public String deleteWishlist (@RequestParam(name="id") String wishlistId, Model model) {
        wishlistService.deleteWishlistById(wishlistId);
        return "redirect:/wishlists";
    }
}
