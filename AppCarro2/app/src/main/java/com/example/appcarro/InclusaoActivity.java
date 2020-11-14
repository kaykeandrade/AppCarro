package com.example.appcarro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

    public class InclusaoActivity extends AppCompatActivity {
        EditText editTextMarca, editTextModel, editTextCor, editTextYear, editTextVal;
        Button buttonConfirmar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inclusao);
            editTextYear = findViewById(R.id.editTextYear);
            editTextCor = findViewById(R.id.editTextCor);
            editTextMarca = findViewById(R.id.editTextMarca);
            editTextModel = findViewById(R.id.editTextModel);
            editTextVal = findViewById(R.id.editTextVal);
            buttonConfirmar = findViewById(R.id.buttonConfirmar);

            buttonConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DtoCarro dtoCarro = new DtoCarro();
                    dtoCarro.setMarca(editTextMarca.getText().toString());
                    dtoCarro.setModelo(editTextModel.getText().toString());
                    dtoCarro.setCor(editTextCor.getText().toString());
                    dtoCarro.setAno(Integer.parseInt(editTextYear.getText().toString()));
                    dtoCarro.setValor(Double.parseDouble(editTextVal.getText().toString()));

                    DaoCarro daoCarro = new DaoCarro(InclusaoActivity.this);
                    try{
                        daoCarro.inserir(dtoCarro);
                        Toast.makeText(InclusaoActivity.this, "Dados Inseridos com Sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InclusaoActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    catch (Exception ex){
                        Toast.makeText(InclusaoActivity.this, "Falha ao inserir", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }