package com.bryansoria.searchbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.LinearLayout;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.AdapterMascota;
import object.Mascota;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Mascota> list;
    RecyclerView rv;
    SearchView searchView;
    AdapterMascota adapter;

    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ref = FirebaseDatabase.getInstance().getReference().child("Mascotas");
        rv = findViewById(R.id.rv);
        searchView = findViewById(R.id.search);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        list = new ArrayList<>();
        adapter = new AdapterMascota(list);
        rv.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){ //Si se llega a eliminar algun registro la aplicacion no se cerrará
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Mascota ms = snapshot.getValue(Mascota.class);
                        list.add(ms);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) { //si el dato del buscador cambia, es decir, cuando haya un objeto que buscar se modificara en tiempo real
                buscar(s);
                return true;
            }
        });

    }

    private void buscar(String s) {
        ArrayList<Mascota> miLista = new ArrayList<>();
        for (Mascota obj: list){
            if (obj.getSeo().toLowerCase().contains(s.toLowerCase())){
                miLista.add(obj);
            }
        }
        AdapterMascota adapter = new AdapterMascota(miLista);
        rv.setAdapter(adapter);
    }
}