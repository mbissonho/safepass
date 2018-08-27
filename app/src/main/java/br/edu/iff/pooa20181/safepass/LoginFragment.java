package br.edu.iff.pooa20181.safepass;

import android.content.Intent;
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

import br.edu.iff.pooa20181.safepass.model.SafePass;
import io.realm.Realm;

public class LoginFragment extends Fragment {

    private Realm realm;
    LoginActivity loginActivity;

    private ViewGroup layout;

    private EditText tEmail, tPass;
    private TextView lRegistry;
    private Button btnLogin;

    public LoginFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        this.loginActivity = (LoginActivity) getContext();
        this.realm = Realm.getDefaultInstance();
        final SafePass safePass = this.realm.where(SafePass.class).findFirst();

        this.layout = view.findViewById(R.id.fragment_login_layout);

        this.tEmail = view.findViewById(R.id.tEmail);
        this.tPass = view.findViewById(R.id.tPass);
        this.btnLogin = view.findViewById(R.id.btnLogin);
        this.lRegistry = view.findViewById(R.id.lRegistry);

        if(safePass == null){
            Toast.makeText(this.loginActivity, "Você deve registrar-se para usar!", Toast.LENGTH_LONG).show();

            this.tEmail.setEnabled(false);
            this.tPass.setEnabled(false);
            this.btnLogin.setEnabled(false);

        }else{
            SafePass.setInstance(safePass);
            this.tEmail.setText(safePass.getEmail());

            this.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(Helper.verifyEmpty(LoginFragment.this.layout)){
                        Helper.launchMessage(LoginFragment.this.loginActivity,"Preencha os campos para logar!");
                    }else{

                        if(safePass.getEmail().compareTo(tEmail.getText().toString()) != 0 || safePass.getMasterPass().compareTo(tPass.getText().toString()) != 0){
                            Helper.launchMessage(LoginFragment.this.loginActivity,"Email e/ou senha inválidos!");
                        }else{
                            Intent intent = new Intent(LoginFragment.this.loginActivity, HomeActivity.class);
                            startActivity(intent);
                            loginActivity.finish();
                        }



                    }


                }
            });

        }





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
