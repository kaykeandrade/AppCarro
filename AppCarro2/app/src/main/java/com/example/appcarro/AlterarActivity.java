package com.example.appcarro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarActivity extends AppCompatActivity {
    EditText editTextMarcaAlterar, editTextModeloAlterar, editTextAnoAlterar, editTextColorAlterar,editTextValorAlterar;
    Button buttonAlterar;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);


        editTextMarcaAlterar = findViewById(R.id.editTextMarcaAlterar);
        editTextModeloAlterar = findViewById(R.id.editTextModeloAlterar);
        editTextColorAlterar = findViewById(R.id.editTextColorAlterar);
        editTextAnoAlterar = findViewById(R.id.editTextAnoAlterar);
        editTextValorAlterar = findViewById(R.id.editTextValorAlterar);
        buttonAlterar = findViewById(R.id.buttonAlterar);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");
        editTextAnoAlterar.setText(bundle.getInt("Ano")+"");
        editTextModeloAlterar.setText(bundle.getString("Modelo"));
        editTextMarcaAlterar.setText(bundle.getString("Marca"));
        editTextColorAlterar.setText(bundle.getString("Cor"));
        editTextValorAlterar.setText(bundle.getDouble("Valor")+"");

        buttonAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DtoCarro dtoCarro = new DtoCarro();
                dtoCarro.setId(id);
                dtoCarro.setMarca(editTextMarcaAlterar.getText().toString());
                dtoCarro.setModelo(editTextModeloAlterar.getText().toString());
                dtoCarro.setAno(Integer.parseInt(editTextAnoAlterar.getText().toString()));
                dtoCarro.setCor(editTextColorAlterar.getText().toString());
                dtoCarro.setValor(Double.parseDouble(editTextValorAlterar.getText().toString()));

                DaoCarro daoCarro = new DaoCarro(AlterarActivity.this);
                try {
                    int linhasInseridas = daoCarro.Update(dtoCarro);
                    if (linhasInseridas>0)
                        Toast.makeText(AlterarActivity.this, "Inserido com sucesso!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AlterarActivity.this, "Algo deu errado!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){
                    Toast.makeText(AlterarActivity.this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(AlterarActivity.this,ListarActivity.class);
                startActivity(intent);
            }
        });
    }
}


