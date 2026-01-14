package net.arcatanium.regalo.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import net.arcatanium.regalo.model.Wishlist;
import net.arcatanium.regalo.model.WishlistItem;
import net.arcatanium.regalo.service.WishlistService;

import java.util.Optional;

@Route("/wishlist")
@PageTitle("Regalo - Wishlist View")
public class WishlistView extends VerticalLayout implements HasUrlParameter<String> {
    private final WishlistService wishlistService;
    private boolean isEditing = false;
    private Wishlist wishlist;

    public WishlistView(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }


    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        showWishlist(parameter);
    }

    private void showWishlist(String wishlistId) {
        removeAll();
        Optional<Wishlist> wishlistOpt = wishlistService.getWishlistById(wishlistId);

        if (wishlistOpt.isPresent()) {
            wishlist = wishlistOpt.get();
            getUI().ifPresent(e -> e.getPage().setTitle("Regalo - " + wishlist.getName()));
            if (isEditing) {
                showEditorView();
            } else {
                showReadOnlyView();
            }
        } else {
            getUI().ifPresent(e -> e.getPage().setTitle("Regalo - Wishlist Not Found"));
            add(new Text("Wishlist not found"));
        }
    }

    private void showReadOnlyView() {
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidthFull();
        topLayout.setAlignItems(Alignment.BASELINE);

        H1 title = new H1(wishlist.getName());

        Button editButton = new Button("Edit Wishlist", e -> {
            isEditing = true;
            showWishlist(wishlist.getId());
        });

        Div topSpacer = new Div();
        topLayout.add(title, topSpacer, editButton);
        topLayout.setFlexGrow(1, topSpacer);
        add(topLayout);

        Grid<WishlistItem> grid = new Grid<>();
        grid.addColumn(wishlistItem -> wishlistItem.getNumber()).setHeader("Number");
        grid.addColumn(wishlistItem -> wishlistItem.getName()).setHeader("Name");
        grid.addColumn(wishlistItem -> wishlistItem.getDescription()).setHeader("Description");
        grid.addColumn(wishlistItem -> wishlistItem.getUrl()).setHeader("URL");
        grid.setItems(wishlist.getWishlistItems());

        setSizeFull();
        grid.setSizeFull();
        add(grid);
    }

    private void showEditorView() {
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidthFull();
        topLayout.setAlignItems(Alignment.BASELINE);

        TextField nameField = new TextField("Wishlist Name");
        nameField.setValue(wishlist.getName());
        nameField.addValueChangeListener(e -> wishlist.setName(e.getValue()));

        Button addItemButton = new Button("Add Item", e -> {
            wishlistService.createNewWishlistItem(wishlist.getId());
            refreshGrid();
        });

        Div topSpacer = new Div();
        topLayout.add(nameField, topSpacer, addItemButton);
        topLayout.setFlexGrow(1, topSpacer);
        add(topLayout);

        Grid<WishlistItem> grid = new Grid<>();

        grid.addColumn(WishlistItem::getNumber).setHeader("Number");

        grid.addComponentColumn(item -> {
            TextField name = new TextField();
            name.setValue(item.getName() != null ? item.getName() : "");
            name.addValueChangeListener(e -> item.setName(e.getValue()));
            return name;
        }).setHeader("Name");

        grid.addComponentColumn(item -> {
            TextField description = new TextField();
            description.setValue(item.getDescription() != null ? item.getDescription() : "");
            description.addValueChangeListener(e -> item.setDescription(e.getValue()));
            return description;
        }).setHeader("Description");

        grid.addComponentColumn(item -> {
            TextField url = new TextField();
            url.setValue(item.getUrl() != null ? item.getUrl() : "");
            url.addValueChangeListener(e -> item.setUrl(e.getValue()));
            return url;
        }).setHeader("URL");

        grid.addComponentColumn(item -> {
            Button deleteButton = new Button(VaadinIcon.TRASH.create());
            deleteButton.addClickListener(e -> {
                wishlistService.deleteWishlistItemByKey(wishlist.getId(), item.getNumber());
                refreshGrid();
            });
            return deleteButton;
        });

        grid.setItems(wishlist.getWishlistItems());

        setSizeFull();
        grid.setSizeFull();
        add(grid);

        HorizontalLayout bottomLayout = new HorizontalLayout();
        bottomLayout.setWidthFull();

        Button saveButton = new Button("Save Wishlist", e -> {
            wishlistService.saveWishlist(wishlist);
            refreshGrid();
        });

        Button returnButton = new Button("Return to View", e -> {
            isEditing = false;
            showWishlist(wishlist.getId());
        });

        Button deleteWishlistButton = new Button("Delete Wishlist", e -> {
            Dialog dialog = new Dialog();
            dialog.setHeaderTitle("Delete Wishlist");
            dialog.add(new Text("Are you sure you want to delete this wishlist?"));

            Button cancelButton = new Button("Cancel", event -> dialog.close());
            Button confirmButton = new Button("Delete", event -> {
                wishlistService.deleteWishlistById(wishlist.getId());
                dialog.close();
                getUI().ifPresent(ui -> ui.navigate(WishlistDirectoryView.class));
            });
            confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

            dialog.getFooter().add(cancelButton);
            dialog.getFooter().add(confirmButton);
            dialog.open();
        });
        deleteWishlistButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Div bottomSpacer = new Div();
        bottomLayout.add(saveButton, returnButton, bottomSpacer, deleteWishlistButton);
        bottomLayout.setFlexGrow(1, bottomSpacer);
        add(bottomLayout);
    }

    private void refreshGrid() {
        Optional<Wishlist> wishlistOpt = wishlistService.getWishlistById(wishlist.getId());
        if (wishlistOpt.isPresent()) {
            wishlist = wishlistOpt.get();
            // Re-render the whole view to refresh grid items and their bindings
            showWishlist(wishlist.getId());
        }
    }
}
