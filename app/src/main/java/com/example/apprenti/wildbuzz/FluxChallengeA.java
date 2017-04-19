package com.example.apprenti.wildbuzz;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class FluxChallengeA extends Fragment {

    public static com.example.apprenti.wildbuzz.FluxChallengeA newInstance() {
        com.example.apprenti.wildbuzz.FluxChallengeA fragment = new com.example.apprenti.wildbuzz.FluxChallengeA();
        return fragment;
    }
    //recyclerview object
    private RecyclerView rView;
    private GridLayoutManager lLayout;
    //adapter object
    AdapterFluxA rcAdapter;
    //Firebase references
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    //progress dialog
    private ProgressDialog progressDialog;
    //list to hold all the uploaded images
    private java.util.List<ContributionsA> rowListItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(android.view.LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_flux_challengea, container, false);
        rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(getActivity(), 3);
        rView = (RecyclerView) view.findViewById(R.id.recyclerView2);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        progressDialog = new ProgressDialog(getActivity());
        rcAdapter = new AdapterFluxA(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
        progressDialog.setMessage("Attendez...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_CONTRIBUTIONS);
        Query query = mDatabase.child(Constants.DATABASE_PATH_ALL_UPLOADS);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                rowListItem.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    ContributionsA participation = postSnapshot.getValue(ContributionsA.class);
                    rowListItem.add(participation);
                    rcAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
        rView.addOnItemTouchListener(new RecyclerItemAClickListener(getActivity(), rView ,new RecyclerItemAClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int itemPosition = rView.getChildLayoutPosition(view);
                        ContributionsA item = rowListItem.get(itemPosition);
                        Intent intent = new Intent(getActivity(), ContributionsA.class);
                        intent.putExtra("contribution",item);
                        intent.putExtra("position",itemPosition);
                        startActivity(intent);
                    }
            @Override public void onLongItemClick(View view, int position) {
            }
                })
        );
        return view;
    }
    private List<ContributionsA> getAllItemList(){
        List<ContributionsA> allItems = new ArrayList<>();
        return allItems;
    }
}