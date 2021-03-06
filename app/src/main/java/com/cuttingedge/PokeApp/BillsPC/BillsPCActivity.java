package com.cuttingedge.PokeApp.BillsPC;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cuttingedge.PokeApp.BaseActivity;
import com.cuttingedge.PokeApp.Pokedex;
import com.cuttingedge.PokeApp.Pokemon;
import com.cuttingedge.PokeApp.PokemonModule;
import com.cuttingedge.PokeApp.R;
import com.cuttingedge.adapter2recycler.Adapter.ModularAdapter;
import com.cuttingedge.adapter2recycler.Adapter.ModularAdapterBuilder;

import java.util.List;

/**
 * Created by Robbe Sneyders
 *
 * Activity to handle Bills PC section in app
 */
public class BillsPCActivity extends BaseActivity {

    ModularAdapter<PokemonModule.PokemonViewHolder, Pokemon> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        setupList();
    }


    /**
     * Sets up the RecyclerView and Adapter
     */
    private void setupList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // List is ordered by pokemon id
        List<Pokemon> pokemonList = Pokedex.getBillsPCID();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        adapter = new ModularAdapterBuilder<>(recyclerView, pokemonList)
                .setSwipeLeft(Color.RED, ResourcesCompat.getDrawable(getResources(), R.drawable.ic_delete_white_24dp, null))
                .setSwipeRight(Color.GREEN, ResourcesCompat.getDrawable(getResources(), R.drawable.ic_cloud_download_white_24dp, null))
                .build();

        new PokemonBillsPCModule().bindToAdapter(adapter);
    }


    /**
     * Reset database and get new list.
     */
    @Override
    protected void reset() {
        Pokedex.initiate(this);

        List<Pokemon> pokemonList = Pokedex.getBillsPCID();
        // swaps the data of the adapter
        adapter.swap(pokemonList);
    }
}
