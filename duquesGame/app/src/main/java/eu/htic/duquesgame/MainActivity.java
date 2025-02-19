package eu.htic.duquesgame;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eu.htic.duquesgame.animations.ConfettiEffect;
import eu.htic.duquesgame.model.DuquesGame;
import eu.htic.duquesgame.model.GuessResult;

public class MainActivity extends AppCompatActivity {

    private DuquesGame game;

    private TextView visor;
    private Button btnBorrar;
    private Button btnEnviar;
    private TableLayout historial;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;

    private List<Button> botonesNumericos;

    private List<Button> botonesDeshabilitados;

    private View gameBodyLayout;
    private ValueAnimator colorAnimator;
    private final Random random = new Random();
    private ConfettiEffect confettiEffect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = findViewById(R.id.visor);
        btnBorrar = findViewById(R.id.btn_borrar);
        btnEnviar = findViewById(R.id.btn_enviar);
        historial = findViewById(R.id.historial);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_0);
        botonesNumericos = List.of(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0);

        TextView gameTitle = findViewById(R.id.game_title);
        gameTitle.setScaleX(0);
        gameTitle.setScaleY(0);
        gameTitle.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(4000)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();

        ImageView gameIcon = findViewById(R.id.helpButton);
        ObjectAnimator bounceAnim = ObjectAnimator.ofFloat(gameIcon, "translationY", 0, -10, 0);
        bounceAnim.setDuration(1000);
        bounceAnim.setRepeatMode(ValueAnimator.REVERSE);
        bounceAnim.setRepeatCount(4);
        bounceAnim.start();

        gameBodyLayout = findViewById(R.id.game_body);

        confettiEffect = new ConfettiEffect(this, (LinearLayout) gameBodyLayout);

        startLavaLampAnimation();

        startNewGame();

        for (Button boton : botonesNumericos) {
            boton.setOnClickListener(v -> {
                if (visor.getText().length() < 4) {
                    // Añadir el dígito al visor
                    visor.append(boton.getText().toString());

                    // Deshabilitar el botón
                    boton.setEnabled(false);

                    // Agregar a la lista de botonesNuméricos deshabilitados
                    botonesDeshabilitados.add(boton);
                }
                if (visor.getText().length() == 4){
                    btnEnviar.setEnabled(true);
                }
            });
        }

        btnBorrar.setOnClickListener(v -> {
            // Verificar si hay dígitos en el visor
            if (visor.getText().length() > 0) {
                // Eliminar el último dígito
                String nuevoTexto = visor.getText().toString().substring(0, visor.getText().length() - 1);
                visor.setText(nuevoTexto);

                // Habilitar el último botón deshabilitado
                if (!botonesDeshabilitados.isEmpty()) {
                    Button ultimoBotonDeshabilitado = botonesDeshabilitados.remove(botonesDeshabilitados.size() - 1);
                    ultimoBotonDeshabilitado.setEnabled(true);

                    // Cambiar el color del botón habilitado
                    // Esto depende de cómo hayas definido el selector de colores
                }
                btnEnviar.setEnabled(false);
            }
        });

        btnEnviar.setOnClickListener(v -> {
            // Verificar que el visor tiene exactamente 4 dígitos
            if (visor.getText().length() == 4) {
                // Añadir el número al historial de números enviados
                String numeroEnviado = visor.getText().toString();
                GuessResult guessResult = game.guess(numeroEnviado);

                // Limpiar el visor
                visor.setText("");

                // Habilitar todos los botonesNuméricos deshabilitados
                for (Button boton : botonesDeshabilitados) {
                    boton.setEnabled(true);
                }
                // Limpiar la lista de botonesNuméricos deshabilitados
                botonesDeshabilitados.clear();

                // Aquí puedes mostrar la lista de números enviados en la UI
                agregarResultado(game.getNumbersTried().size(), numeroEnviado, guessResult.getKills(), guessResult.getWounds());

                if (guessResult.playerWon()) {
                    onPlayerWins();
                } else {
                    btnEnviar.setEnabled(false);
                }
            } else {
                Toast.makeText(this, "El número debe tener 4 dígitos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showVictoryAnimation() {
        confettiEffect.startConfetti();
    }
    private void showVictoryDialog() {
        new AlertDialog.Builder(this)
                .setTitle("¡Victoria!")
                .setMessage("¡Felicidades! Has adivinado el número correctamente en " + game.getNumbersTried().size() + " intentos.")
                .setPositiveButton("Jugar de nuevo", (dialog, which) -> startNewGame())
                .setNegativeButton("Salir", (dialog, which) -> finish())
                .show();
    }

    private void startNewGame() {
        game = new DuquesGame();
        visor.setText("");

        botonesDeshabilitados = new ArrayList<>();

        for (Button boton : botonesNumericos) {
            boton.setEnabled(true);
        }

        btnEnviar.setEnabled(false);

        // Limpia el historial
        int childCount = historial.getChildCount();
        if (childCount > 2) {
            historial.removeViews(2, childCount - 2);
        }
    }

    private void agregarResultado(int intento, String numero, int muertos, int heridos) {
        TableRow fila = new TableRow(this);

        // Configurar la primera columna (Intento)
        TextView txtIntento = new TextView(this);
        txtIntento.setText(String.valueOf(intento));
        txtIntento.setPadding(8, 8, 8, 8);
        txtIntento.setGravity(Gravity.CENTER);
        TableRow.LayoutParams paramsIntento = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3);
        txtIntento.setLayoutParams(paramsIntento);

        // Configurar la segunda columna (Número)
        TextView txtNumero = new TextView(this);
        txtNumero.setText(numero);
        txtNumero.setPadding(8, 8, 8, 8);
        txtNumero.setGravity(Gravity.CENTER);
        TableRow.LayoutParams paramsNumero = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 2);
        txtNumero.setLayoutParams(paramsNumero);

        // Configurar la tercera columna (Muertos)
        TextView txtMuertos = new TextView(this);
        txtMuertos.setText(String.valueOf(muertos));
        txtMuertos.setPadding(8, 8, 8, 8);
        txtMuertos.setGravity(Gravity.CENTER);
        TableRow.LayoutParams paramsMuertos = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
        txtMuertos.setLayoutParams(paramsMuertos);

        // Configurar la cuarta columna (Heridos)
        TextView txtHeridos = new TextView(this);
        txtHeridos.setText(String.valueOf(heridos));
        txtHeridos.setPadding(8, 8, 8, 8);
        txtHeridos.setGravity(Gravity.CENTER);
        TableRow.LayoutParams paramsHeridos = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1);
        txtHeridos.setLayoutParams(paramsHeridos);

        // Agregar columnas a la fila
        fila.addView(txtIntento);
        fila.addView(txtNumero);
        fila.addView(txtMuertos);
        fila.addView(txtHeridos);

        // Agregar fila al TableLayout
        historial.addView(fila, 2);
    }

    public void showRules(View view) {
        String instrucciones = "Encuentra un número aleatorio de 4 cifras\n" +
                "Los dígitos no pueden repetirse.\n" +
                "Cada intento será respondido con: \n" +
                " -> número de dígitos \"muertos\": dígitos que se encuentran en la misma posición en el número introducido y en el número que se debe adivirnar.\n" +
                " -> número de dígitios \"heridos\": dígitos que se encontrarn en el número introducido y en el número que se debe adivirnar, pero en diferente posición.\n" +
                "El juego termina cuando el jugador encuentra el número que se debe adivinar, es decir, con una respuesta con 4 muertos.";
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Instrucciones del Juego")
                .setMessage(instrucciones)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void startLavaLampAnimation() {
        int colorStart = getRandomLavaColor();
        int colorEnd = getRandomLavaColor();
        int duration = 12000 + + random.nextInt(6000);

        colorAnimator = ObjectAnimator.ofObject(gameBodyLayout, "backgroundColor",
                new ArgbEvaluator(), colorStart, colorEnd);

        colorAnimator.setDuration(duration);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimator.setRepeatCount(ValueAnimator.INFINITE);

        colorAnimator.start();
    }

    private void stopBackgroundColorAnimation() {
        if (colorAnimator != null) {
            colorAnimator.cancel();
        }
    }

    private int getRandomLavaColor() {
        int[] lavaColors = {
                Color.parseColor("#FFDDC1"), // Melocotón suave
                Color.parseColor("#FFD1DC"), // Rosa pastel
                Color.parseColor("#C1E1FF"), // Azul cielo claro
                Color.parseColor("#D4C1FF"), // Lavanda suave
                Color.parseColor("#B6E2D3"), // Verde menta
                Color.parseColor("#FFFACD")  // Amarillo vainilla
        };

        return lavaColors[random.nextInt(lavaColors.length)];
    }

    private void onPlayerWins() {
        stopBackgroundColorAnimation();
        showVictoryAnimation();
        showVictoryDialog();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopBackgroundColorAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLavaLampAnimation();
    }
}