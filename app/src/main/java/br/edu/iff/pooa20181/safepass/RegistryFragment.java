package br.edu.iff.pooa20181.safepass;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistryFragment extends Fragment {

    private TextView tEmail, tPass, tRePass;
    private Button btnSubmit;

    public RegistryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registry, container, false);

        this.tEmail = view.findViewById(R.id.tEmail);
        this.tPass = view.findViewById(R.id.tPass);
        this.tRePass = view.findViewById(R.id.tRePass);
        this.btnSubmit = view.findViewById(R.id.btnSubmit);

        this.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Teste!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }



}
