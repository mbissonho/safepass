package br.edu.iff.pooa20181.safepass.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.edu.iff.pooa20181.safepass.ClickRecyclerViewListener;
import br.edu.iff.pooa20181.safepass.R;
import br.edu.iff.pooa20181.safepass.model.Conta;

public class ContaAdapter extends RecyclerView.Adapter{

    private List<Conta> contas;
    private Context ctx;
    private static ClickRecyclerViewListener clickRecyclerViewListener;


    public ContaAdapter(List<Conta> contas, Context ctx, ClickRecyclerViewListener clickRecyclerViewListener){
        this.contas = contas;
        this.ctx = ctx;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.ctx).inflate(R.layout.item_conta, parent, false);
        ContaHolder contaHolder = new ContaHolder(view);
        contaHolder.setContext(this.ctx);

        return contaHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ContaHolder contaHolder = (ContaHolder) holder;

        Conta conta = this.contas.get(position);

        contaHolder.setConta(conta);
        contaHolder.nomeDaConta.setText(conta.getNomedaConta());
        contaHolder.bindListeners();
    }



    @Override
    public int getItemCount() {
        return this.contas.size();
    }

    public class ContaHolder extends RecyclerView.ViewHolder {

        private Context context;
        private ImageButton btnVer, btnWeb;
        private TextView nomeDaConta;
        private Conta conta;

        public ContaHolder(View view){
            super(view);

            this.nomeDaConta = view.findViewById(R.id.tNomeConta);
            this.btnVer = view.findViewById(R.id.btnVer);
            this.btnWeb = view.findViewById(R.id.btnWeb);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(ContaAdapter.this.contas.get(getLayoutPosition()));
                }
            });
        }

        public void setConta(Conta conta) {
            this.conta = conta;
        }
        public void setContext(Context context) {this.context = context;}

        private void bindListeners(){

            this.btnVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Conta conta = ContaHolder.this.conta;

                    AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                    dialog.setTitle("Conta["+conta.getId()+"]: "+conta.getNomedaConta());

                    dialog.setMessage(
                        "Web: "+conta.getUrlSite()+"\n"+
                        "Login: "+conta.getLogin()+"\n"+
                        "Senha: "+conta.getHashPassword()+"\n"+
                        "Notas: "+conta.getNotas()+"\n"
                    );

                    dialog.setNeutralButton("OK",null);
                    dialog.show();
                }
            });

            btnWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String url = "https://"+ContaHolder.this.conta.getUrlSite();
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }
            });

        }

    }//ContaHolder

}
