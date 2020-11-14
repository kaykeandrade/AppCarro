package com.example.appcarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {
    ListView listViewSelect;
    ArrayList<DtoCarro> arrayListCarro = new ArrayList<>();
    DtoCarro carro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listViewSelect = findViewById(R.id.listViewSelect);
        DaoCarro daoCarro = new DaoCarro(ListarActivity.this);
        listViewSelect.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                carro = arrayListCarro.get(position);
                registerForContextMenu(listViewSelect);
                return false;
            }
        });

        arrayListCarro = daoCarro.consultar();

        atualizarList();
    }

    private void atualizarList() {
        ArrayAdapter adapter = new ArrayAdapter(ListarActivity.this, android.R.layout.simple_list_item_1, arrayListCarro);
        listViewSelect.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0, "Alterar");
        menu.add(0,1,1, "Deletar");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0) {
            Intent intent = new Intent(ListarActivity.this, AlterarActivity.class);
            intent.putExtra("Marca", carro.getMarca());
            intent.putExtra("Modelo", carro.getModelo());
            intent.putExtra("Cor", carro.getCor());
            intent.putExtra("Ano", carro.getAno());
            intent.putExtra("Valor", carro.getValor());
            startActivity(intent);
        } else {
            deletar();
        }
        return super.onContextItemSelected(item);
    }

    private void deletar() {
        AlertDialog.Builder msg = new AlertDialog.Builder(ListarActivity.this);
        msg.setMessage("Deseja confirmar a exclusão?");
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DaoCarro daoCarro = new DaoCarro(ListarActivity.this);
                int deletados = daoCarro.excluir(carro);
                if (deletados > 0) {
                    Toast.makeText(ListarActivity.this, "Excluído com Sucesso", Toast.LENGTH_SHORT).show();
                    arrayListCarro = daoCarro.consultar();
                    atualizarList();
                } else {
                    Toast.makeText(ListarActivity.this, "Falha ao deletar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        msg.setNegativeButton("Não", null);
        msg.show();
    }
}

