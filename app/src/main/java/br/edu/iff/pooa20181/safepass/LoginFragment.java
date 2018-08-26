package br.edu.iff.pooa20181.safepass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private EditText tEmail, tPass;
    private TextView lRegistry;
    private Button btnLogin;

    public LoginFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        this.tEmail = view.findViewById(R.id.tEmail);
        this.tPass = view.findViewById(R.id.tPass);
        this.btnLogin = view.findViewById(R.id.btnLogin);
        this.lRegistry = view.findViewById(R.id.lRegistry);

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(),
                        "Teste!", Toast.LENGTH_LONG).show();
            }
        });

        this.lRegistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RegistryFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentArea, fragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
