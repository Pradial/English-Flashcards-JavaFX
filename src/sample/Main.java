package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import traductor.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public Button salir = new Button("Salir");

    @Override
    public void start(Stage window){
        Traductor traductor = new Traductor();
        Diccionario palabrasAEntrenar = new Diccionario();
        Puntos puntaje = new Puntos();


        // First Scene
        Text nombrePrograma = new Text("English Flashcards Game");
        nombrePrograma.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Text titulo = new Text("Elegí la opción que buscas:");
        titulo.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Button opcion1 = new Button("Traducir palabras\nEspañol->Inglés");
        Button opcion2 = new Button("Traducir palabras\nInglés->Español");
        Button opcion3 = new Button("Entrenar");
        Button opcion4 = new Button("Estadísticas");
        Button guardar = new Button("Guardar");
        Button vaciarDiccionario = new Button("Vaciar diccionario\n    en memoria");
        opcion1.setMinSize(120, 60);
        opcion2.setMinSize(120, 60);
        opcion3.setMinSize(120, 60);
        opcion4.setMinSize(120, 60);
        vaciarDiccionario.setMinSize(120, 60);
        guardar.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        salir.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

        HBox hboxGuardarSalir = new HBox();
        hboxGuardarSalir.setPadding(new Insets(10, 10, 10, 10));
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        spacer.setMinSize(10, 1);
        hboxGuardarSalir.getChildren().addAll(guardar, spacer, salir);

        HBox hboxTitulo = new HBox();
        hboxTitulo.getChildren().add(nombrePrograma);
        hboxTitulo.setAlignment(Pos.CENTER);

        GridPane gridOpciones = new GridPane();
        gridOpciones.setAlignment(Pos.CENTER);
        gridOpciones.setHgap(10);
        gridOpciones.setVgap(10);
        gridOpciones.setPadding(new Insets(25, 25, 25, 25));
        gridOpciones.add(titulo, 0,0, 4, 1);
        gridOpciones.add(opcion1, 1,1, 4, 1);
        gridOpciones.add(opcion2, 1,2, 4, 1);
        gridOpciones.add(opcion3, 1, 3, 4, 1);
        gridOpciones.add(opcion4, 1, 4, 4, 1);
        gridOpciones.add(vaciarDiccionario,1,5,4,1);
        GridPane.setHalignment(opcion1, HPos.CENTER);
        GridPane.setHalignment(opcion2, HPos.CENTER);
        GridPane.setHalignment(opcion3, HPos.CENTER);
        GridPane.setHalignment(opcion4, HPos.CENTER);
        GridPane.setHalignment(vaciarDiccionario, HPos.CENTER);

        BorderPane primerLayout = new BorderPane();
        primerLayout.setTop(hboxTitulo);
        primerLayout.setCenter(gridOpciones);
        primerLayout.setBottom(hboxGuardarSalir);

        Scene homeScene = new Scene(primerLayout, 800,600);

        vaciarDiccionario.setOnAction((event)->palabrasAEntrenar.setEmpty());


        // Second scene (Traducir Español - Inglés)
        TextField palabra = new TextField();
        Label word = new Label();
        Button botonTraducir = new Button("Traducir");
        Button botonVolver = new Button("Volver");
        Button agregarPalabras = new Button("Agregar Palabras");
        Label advertencia = new Label("No todas las palabras se encuentran en el diccionario, o la traducción puede ser errónea.\n" +
                "Podes Buscarlas en otro diccionario y agregar manualmente las traducciones que querés practicar");

        GridPane gridTraducir = new GridPane();
        gridTraducir.add(palabra, 0, 0);
        gridTraducir.add(botonTraducir, 0, 1);
        gridTraducir.add(word, 0, 2);
        gridTraducir.setAlignment(Pos.CENTER);
        gridTraducir.setHgap(10);
        gridTraducir.setVgap(10);
        GridPane.setHalignment(palabra, HPos.CENTER);
        GridPane.setHalignment(botonTraducir, HPos.CENTER);
        GridPane.setHalignment(word, HPos.CENTER);

        HBox bottomRight = new HBox(agregarPalabras);
        bottomRight.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(bottomRight, Priority.ALWAYS);
        HBox bottomLeft = new HBox(botonVolver);
        bottomLeft.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(bottomLeft, Priority.ALWAYS);
        HBox hboxVolverAgregarPalabras = new HBox(bottomLeft, advertencia, bottomRight);
        hboxVolverAgregarPalabras.setPadding(new Insets(10, 10, 10, 10));

        BorderPane layoutTraducir = new BorderPane();
        layoutTraducir.setCenter(gridTraducir);
        layoutTraducir.setBottom(hboxVolverAgregarPalabras);

        Scene scene1 = new Scene(layoutTraducir,800,600);

        botonTraducir.setOnAction((event) -> {
                if (traductor.contieneLaPalabraEnEsp(palabra.getText())){
                    StringBuilder respuestaCorrecta = new StringBuilder();
                    ArrayList<String> asd;
                    asd = traductor.traducir(palabra.getText());
                    for(String s: asd){respuestaCorrecta.append(s).append("\n");}
                    word.setText("La palabra significa:\n" + respuestaCorrecta);
                    palabrasAEntrenar.addTraduccion(palabra.getText(),String.valueOf(traductor.traducir(palabra.getText())));
                } else word.setText("La palabra no se encuentra en el diccionario");
        });

        botonVolver.setOnAction((event)->window.setScene(homeScene));


        // Third scene (Traducir Inglés - Español)
        TextField palabra2 = new TextField();
        Label word2 = new Label();
        Button botonTraducir2 = new Button("Traducir");
        Button botonVolver2 = new Button("Volver");
        Button agregarPalabras2 = new Button("Agregar Palabras");
        Label advertencia2 = new Label("No todas las palabras se encuentran en el diccionario, o la traducción puede ser errónea.\n" +
                "Podes Buscarlas en otro diccionario y agregar manualmente las traducciones que querés practicar");

        GridPane gridTraducir2 = new GridPane();
        gridTraducir2.add(palabra2, 0, 0);
        gridTraducir2.add(botonTraducir2, 0, 1);
        gridTraducir2.add(word2, 0, 2);
        gridTraducir2.setAlignment(Pos.CENTER);
        gridTraducir2.setHgap(10);
        gridTraducir2.setVgap(10);
        GridPane.setHalignment(palabra2, HPos.CENTER);
        GridPane.setHalignment(botonTraducir2, HPos.CENTER);
        GridPane.setHalignment(word2, HPos.CENTER);

        HBox bottomRight2 = new HBox(agregarPalabras2);
        bottomRight2.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(bottomRight2, Priority.ALWAYS);
        HBox bottomLeft2 = new HBox(botonVolver2);
        bottomLeft2.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(bottomLeft2, Priority.ALWAYS);
        HBox hboxVolverAgregarPalabras2 = new HBox(bottomLeft2, advertencia2, bottomRight2);
        hboxVolverAgregarPalabras2.setPadding(new Insets(10, 10, 10, 10));

        BorderPane layoutTraducir2 = new BorderPane();
        layoutTraducir2.setCenter(gridTraducir2);
        layoutTraducir2.setBottom(hboxVolverAgregarPalabras2);

        Scene scene4 = new Scene(layoutTraducir2,800,600);

        botonTraducir2.setOnAction((event) -> {
            if (traductor.contieneLaPalabraEnIng(palabra2.getText())){
                StringBuilder respuestaCorrecta = new StringBuilder();
                ArrayList<String> asd;
                asd = traductor.translate(palabra2.getText());
                for(String s: asd){respuestaCorrecta.append(s).append("\n");}
                word2.setText("La traudcción es:\n" + respuestaCorrecta);
                palabrasAEntrenar.addTraduccion(palabra2.getText(),String.valueOf(traductor.translate(palabra2.getText())));
            } else word2.setText("The word is not on the dictionary");
        });

        botonVolver2.setOnAction((event)->window.setScene(homeScene));

        // Fourth Scene (Entrenar)
        Label encabezado = new Label("La palabra es: ");
        Label prueba = new Label("");
        TextField respuesta = new TextField();
        Label correcto = new Label("");
        Button responder = new Button("Responder");
        Button rendirse = new Button("Rendirse");
        Button volver2 = new Button("Volver");
        rendirse.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        volver2.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

        HBox hboxVolverRendirse = new HBox();
        hboxVolverRendirse.setPadding(new Insets(10, 10, 10, 10));
        Pane spacer2 = new Pane();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        spacer2.setMinSize(10, 1);
        hboxVolverRendirse.getChildren().addAll(volver2, spacer2, rendirse);

        GridPane gridEntrenar = new GridPane();
        gridEntrenar.add(encabezado, 0, 0);
        gridEntrenar.add(prueba, 0, 1);
        gridEntrenar.add(respuesta, 0, 2);
        gridEntrenar.add(responder, 0, 3);
        gridEntrenar.add(correcto, 0, 4);
        gridEntrenar.setAlignment(Pos.CENTER);
        gridEntrenar.setHgap(10);
        gridEntrenar.setVgap(10);
        gridEntrenar.setPadding(new Insets(25, 25, 25, 25));
        GridPane.setHalignment(encabezado, HPos.CENTER);
        GridPane.setHalignment(prueba, HPos.CENTER);
        GridPane.setHalignment(responder, HPos.CENTER);
        GridPane.setHalignment(respuesta, HPos.CENTER);
        GridPane.setHalignment(correcto, HPos.CENTER);

        BorderPane layout2 = new BorderPane();
        layout2.setCenter(gridEntrenar);
        layout2.setBottom(hboxVolverRendirse);

        Scene scene2 = new Scene(layout2, 800, 600);

        rendirse.setOnAction((event)->{
            StringBuilder respuestaCorrecta = new StringBuilder();
            String asd = palabrasAEntrenar.traduccion(prueba.getText());
            String[] qwe = asd.split(", ");
            for(String s: qwe){respuestaCorrecta.append(s).append("\n");}
            correcto.setText(":(\n\nArriba el ánimo, que ya te vas a acordar de la palabra!!\n\nLa palabra significaba:\n"
            + respuestaCorrecta);
            puntaje.sumarIncorrecta();
            prueba.setText(palabrasAEntrenar.palabraRandom());
        });

        responder.setOnAction((event)->{
            if (palabrasAEntrenar.getValue(prueba.getText()).contains(respuesta.getText())){
                correcto.setText("CORRECTO!");
                prueba.setText(palabrasAEntrenar.palabraRandom());
                puntaje.sumarCorrecta();
            }else {
                correcto.setText("Incorrecto :(\nProbá devuelta");
                puntaje.sumarIncorrecta();
            }

        });

        volver2.setOnAction((action)->window.setScene(homeScene));


        // Fifth Scene (Estadísticas)
        Button resetear = new Button("Resetear estadísticas");
        Button volver3 = new Button("Volver");
        resetear.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        volver3.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
        Label total = new Label("Total de veces entrenado: " + puntaje.getTotales());
        Label correctas = new Label("Respuestas correctas: " + puntaje.getCorrectas());
        Label incorrectas = new Label("Respuestas incorrectas: " + puntaje.getIncorrectas());
        Label nota = new Label("Tu nota es de: " + puntaje.getNota());

        GridPane gridEstadisticas = new GridPane();
        gridEstadisticas.add(total, 0, 0);
        gridEstadisticas.add(correctas, 0, 1);
        gridEstadisticas.add(incorrectas, 0, 2);
        gridEstadisticas.add(nota, 0, 3);
        gridEstadisticas.setAlignment(Pos.CENTER);
        gridEstadisticas.setHgap(10);
        gridEstadisticas.setVgap(10);
        gridEstadisticas.setPadding(new Insets(25, 25, 25, 25));
        GridPane.setHalignment(total, HPos.CENTER);
        GridPane.setHalignment(correctas, HPos.CENTER);
        GridPane.setHalignment(incorrectas, HPos.CENTER);
        GridPane.setHalignment(nota, HPos.CENTER);

        HBox hboxVolverResetear = new HBox();
        hboxVolverResetear.setPadding(new Insets(10, 10, 10, 10));
        Pane spacer3 = new Pane();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        spacer3.setMinSize(10, 1);
        hboxVolverResetear.getChildren().addAll(volver3, spacer3, resetear);

        BorderPane layoutEstadisticas = new BorderPane();
        layoutEstadisticas.setCenter(gridEstadisticas);
        layoutEstadisticas.setBottom(hboxVolverResetear);

        Scene scene3 = new Scene(layoutEstadisticas, 800, 600);

        volver3.setOnAction((action)->window.setScene(homeScene));

        resetear.setOnAction((action)->{
            puntaje.resetearEstadisticas();
            opcion4.fire();
        });


        // Sixth Scene (Agregar Palabras)
        TextField addPalabra = new TextField();
        TextField addWord = new TextField();
        Label add = new Label("Palabra a agregar: ");
        Label add2 = new Label("Traducción: ");
        Button agregar = new Button("Agregar");
        agregar.setMinSize(80, 50);
        Button volverAHome = new Button("Volver");
        Label agregada = new Label("");

        GridPane addWords = new GridPane();
        addWords.add(add, 0, 0);
        addWords.add(addPalabra, 1, 0);
        addWords.add(add2, 0, 1);
        addWords.add(addWord, 1, 1);
        addWords.add(agregar,0,2, 2,1);
        addWords.add(agregada,0,3, 2,1);
        addWords.setAlignment(Pos.CENTER);
        addWords.setHgap(10);
        addWords.setVgap(10);
        addWords.setPadding(new Insets(25, 25, 25, 25));
        GridPane.setHalignment(agregar, HPos.CENTER);
        GridPane.setHalignment(agregada, HPos.CENTER);

        HBox hboxVolverHome = new HBox();
        hboxVolverHome.getChildren().add(volverAHome);
        hboxVolverHome.setPadding(new Insets(10,10,10, 10));

        BorderPane layoutAddWords = new BorderPane();
        layoutAddWords.setCenter(addWords);
        layoutAddWords.setBottom(hboxVolverHome);

        Scene addingWords = new Scene(layoutAddWords, 800, 600);

        agregar.setOnAction((action)->{
            palabrasAEntrenar.addTraduccion(addPalabra.getText(), addWord.getText());
            agregada.setText("Agregada!");
        });

        volverAHome.setOnAction((action)->window.setScene(homeScene));

        // Funcionalidad de los botones
        opcion1.setOnAction((action)->window.setScene(scene1));
        opcion2.setOnAction((action)->window.setScene(scene4));
        opcion3.setOnAction((action)->{
            prueba.setText(palabrasAEntrenar.palabraRandom());
            window.setScene(scene2);
        });
        opcion4.setOnAction((action)->{
            window.setScene(scene3);
            total.setText("Total de veces entrenado: " + puntaje.getTotales());
            correctas.setText("Respuestas correctas: " + puntaje.getCorrectas());
            incorrectas.setText("Respuestas incorrectas: " + puntaje.getIncorrectas());
            nota.setText("Tu nota es de: " + puntaje.getNota());
        });
        agregarPalabras.setOnAction((event)->window.setScene(addingWords));

        salir.setOnAction((event)->closeButtonAction());

        guardar.setOnAction((action) -> {
            puntaje.guardarPuntaje();
            palabrasAEntrenar.guardar();
        });

        window.setScene(homeScene);
        window.show();
    }

    private void closeButtonAction(){
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
