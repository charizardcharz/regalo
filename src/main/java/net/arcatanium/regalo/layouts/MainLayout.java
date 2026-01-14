package net.arcatanium.regalo.layouts;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Layout;

@Layout
public class MainLayout extends AppLayout {
    public MainLayout() {
        H1 title = new H1("Regalo");
        addToNavbar(title);
    }
}
