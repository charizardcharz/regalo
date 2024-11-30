/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package net.arcatanium.regalo.controller;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.model.jpa.WishlistItemEntity;
import net.arcatanium.regalo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addNewWishlist() {
        WishlistEntity wishlistEntity = wishlistService.createNewWishlist();
        return "redirect:display?id=" + wishlistEntity.getWishlistId().toString();
    }

    @GetMapping("/wishlist/new-item")
    public String addNewWishlistItem(@RequestParam String wishlistId, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        Optional<WishlistItemEntity> wishlistItemEntityOptional = wishlistService.createNewWishlistItem(wishlistId);

        if (wishlistItemEntityOptional.isPresent()) {
            return "redirect:" + referrer;
        } else {
            return "error";
        }
    }

    @GetMapping("/wishlist/delete-item")
    public String deleteWishlistItem (@RequestParam String wishlistId, @RequestParam Integer sequenceNumber, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        wishlistService.deleteWishlistItemByKey(wishlistId, sequenceNumber);
        return "redirect:" + referrer;
    }

    @PostMapping("/wishlist/save")
    public String editWishlistByIdSubmit(@ModelAttribute Wishlist wishlist, Model model , @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        wishlistService.saveWishlist(wishlist);
        return "redirect:" + referrer;
    }

    @GetMapping("/wishlist/delete")
    public String deleteWishlist (@RequestParam(name="id") String wishlistId, Model model) {
        wishlistService.deleteWishlistById(wishlistId);
        return "redirect:/wishlists";
    }
}
