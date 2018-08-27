package br.edu.iff.pooa20181.safepass;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.edu.iff.pooa20181.safepass.model.SafePass;
import io.realm.Realm;

public class RegistryFragment extends Fragment {

    Realm realm;

    private EditText tEmail, tPass, tRePass;
    private TextView lBack;
    private Button btnSubmit;

    private ViewGroup layout;

    public RegistryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registry, container, false);

        this.layout = view.findViewById(R.id.fragment_registry_layout);

        this.tEmail = view.findViewById(R.id.tEmail);
        this.tPass = view.findViewById(R.id.tPass);
        this.tRePass = view.findViewById(R.id.tRePass);
        this.btnSubmit = view.findViewById(R.id.btnSubmit);
        this.lBack = view.findViewById(R.id.lBack);

        if(SafePass.getInstance() == null){
            this.btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(Helper.verifyEmpty(RegistryFragment.this.layout)){
                        Helper.launchMessage(getContext(), "Preencha todos os campos!");
                    }else{

                        String pass, repass;

                        pass = RegistryFragment.this.tPass.getText().toString();
                        repass = RegistryFragment.this.tRePass.getText().toString();



                        if(pass.compareTo(repass) != 0){
                            Helper.launchMessage(getContext(), "As senhas informadas não são iguais!");
                        }else{

                            RegistryFragment.this.btnSubmit.setEnabled(false);

                            RegistryFragment.this.realm = Realm.getDefaultInstance();

                            RegistryFragment.this.realm.beginTransaction();

                            SafePass safePass = new SafePass();
                            safePass.setEmail(RegistryFragment.this.tEmail.getText().toString());
                            safePass.setMasterPass(pass);
                            SafePass.setInstance(safePass);

                            RegistryFragment.this.realm.copyToRealm(safePass);
                            RegistryFragment.this.realm.commitTransaction();
                            RegistryFragment.this.realm.close();

                            Helper.launchMessage(getContext(), "Cadastrado efetuado com sucesso!");

                            RegistryFragment.this.backToLogin();
                        }


                    }

                }
            });
        }else{
            this.tEmail.setEnabled(false);
            this.tPass.setEnabled(false);
            this.tRePass.setEnabled(false);
            this.btnSubmit.setEnabled(false);
            Helper.launchMessage(getContext(), "Já existe uma conta cadastrada. Basta entrar!");
        }


        this.lBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistryFragment.this.backToLogin();
            }
        });

        return view;
    }

    private void backToLogin(){
        Fragment fragment = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentArea, fragment);
        fragmentTransaction.commit();
    }


}
