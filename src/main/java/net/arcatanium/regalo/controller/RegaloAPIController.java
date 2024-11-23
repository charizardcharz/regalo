package net.arcatanium.regalo.controller;

import lombok.extern.slf4j.Slf4j;
import net.arcatanium.regalo.exception.ResourceNotFoundException;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.jpa.WishlistEntity;
import net.arcatanium.regalo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class RegaloAPIController {
    private final WishlistService wishlistService;

    @Autowired
    public RegaloAPIController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping(value = "/wishlists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Wishlist>> getAllWishlist() {
        List<Wishlist> wishlistList = wishlistService.getAllWishlists();

        if (CollectionUtils.isEmpty(wishlistList)) {
            throw new ResourceNotFoundException("No wishlists in database");
        }

        return new ResponseEntity<>(wishlistList, HttpStatus.OK);
    }

    @GetMapping(value = "/wishlist/{wishlistId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Wishlist> getWishlist(@PathVariable String wishlistId) {
        Optional<Wishlist> wishlistOptional = wishlistService.getWishlistById(wishlistId);

        if (wishlistOptional.isEmpty()) {
            throw new ResourceNotFoundException("Unable to find wishlist with id " + wishlistId);
        }

        return new ResponseEntity<>(wishlistOptional.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/wishlist/save")
    public ResponseEntity<WishlistEntity> saveWishlist(@RequestBody Wishlist wishlist){
        Optional<WishlistEntity> wishlistEntityOptional = Optional.ofNullable(wishlistService.saveWishlist(wishlist));

        if (wishlistEntityOptional.isEmpty()){
            return ResponseEntity.internalServerError().build();
        }

        return new ResponseEntity<>(wishlistEntityOptional.get(), HttpStatus.OK);
    }

    @PutMapping(value = "/wishlist/{wishlistId}/update")
    public ResponseEntity<WishlistEntity> updateWishlist(@RequestBody Wishlist wishlist){
        Optional<WishlistEntity> wishlistEntityOptional = Optional.ofNullable(wishlistService.saveWishlist(wishlist));

        if (wishlistEntityOptional.isEmpty()){
            return ResponseEntity.internalServerError().build();
        }

        return new ResponseEntity<>(wishlistEntityOptional.get(), HttpStatus.OK);
    }
}
