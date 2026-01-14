package net.arcatanium.regalo.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.service.WishlistService;

import java.util.List;

@Route("/")
@PageTitle("Regalo - All Wishlists")
@Menu(order = 0, icon = "vaadin:clipboard-check", title = "All Wishlists")
public class WishlistDirectoryView extends VerticalLayout {
    WishlistDirectoryView(WishlistService wishlistService) {
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidthFull();
        topLayout.setAlignItems(Alignment.BASELINE);

        H2 title = new H2("All Wishlists");

        Button newWishlistButton = new Button("New Wishlist", e -> {
            wishlistService.createNewWishlist();
        });

        Div topSpacer = new Div();
        topLayout.add(title, topSpacer, newWishlistButton);
        topLayout.setFlexGrow(1, topSpacer);
        add(topLayout);

        List<Wishlist> wishlistList = wishlistService.getAllWishlists();

        Grid<Wishlist> grid = new Grid<>();
        grid.addColumn(wishlist -> wishlist.getName()).setHeader("Name");
        grid.addColumn(wishlist -> wishlist.getId()).setHeader("ID");
        grid.addColumn(new ComponentRenderer<>(wishlist -> {
            Button button = new Button("Show Wishlist");
            button.addClickListener(e -> button.getUI().ifPresent(ui ->
                    ui.navigate(WishlistView.class, wishlist.getId())));
            return button;
        })).setHeader("Link");
        grid.setItems(wishlistList);

        setSizeFull();
        grid.setSizeFull();
        add(grid);
    }
}
