package br.edu.iff.pooa20181.safepass;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

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

        private ImageButton btnVer, btnWeb;
        private TextView nomeDaConta;
        private Conta conta;

        public ContaHolder(View view){
            super(view);

            this.nomeDaConta= view.findViewById(R.id.tNomeConta);
            this.btnVer = view.findViewById(R.id.btnVer);
            this.btnWeb = view.findViewById(R.id.btnWeb);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickRecyclerViewListener.onClick(ContaAdapter.this.contas.get(getLayoutPosition()));
                }
            });
        }

        public Conta getConta() {
            return conta;
        }

        public void setConta(Conta conta) {
            this.conta = conta;
        }

        private void bindListeners(){

            this.btnVer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"Clicou!", Toast.LENGTH_SHORT).show();
                }
            });

            btnWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Clicou!", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

}
