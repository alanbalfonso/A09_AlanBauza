package com.example.a09_alanbauza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.a09_alanbauza.DashboardFragment;
import com.example.a09_alanbauza.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inicializar vistas
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // 2. Configurar la Toolbar como ActionBar
        setSupportActionBar(toolbar);

        // 3. Configurar el botón de hamburguesa (ActionBarDrawerToggle)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, // String resource para accesibilidad
                R.string.navigation_drawer_close // String resource para accesibilidad
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 4. Establecer el Listener para la selección del menú
        navigationView.setNavigationItemSelectedListener(this);

        // 5. Cargar el Fragment de Inicio por defecto
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_home); // Marcar "Inicio" como seleccionado
        }
    }

    /**
     * Maneja la selección de ítems en el NavigationView.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Obtener el ID del ítem seleccionado
        int id = item.getItemId();
        Fragment selectedFragment = null;

        if (id == R.id.nav_home) {
            selectedFragment = new HomeFragment();
            // Opcional: Cambiar el título de la Toolbar
            toolbar.setTitle("Inicio");
        } else if (id == R.id.nav_dashboard) {
            selectedFragment = new DashboardFragment();
            toolbar.setTitle("Dashboard");
        }

        // Cargar el Fragment si se seleccionó uno
        if (selectedFragment != null) {
            loadFragment(selectedFragment);
        }

        // Cerrar el DrawerLayout después de la selección
        drawer.closeDrawers();
        return true; // Indica que el ítem ha sido manejado
    }

    /**
     * Función para reemplazar el Fragment actual en el FrameLayout
     */
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment) // Reemplaza en el FrameLayout
                .commit();
    }
}