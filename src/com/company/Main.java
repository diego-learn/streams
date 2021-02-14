package com.company;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<Integer> lit = new ArrayList<>();

        lit.add(2);
        lit.add(6);
        lit.add(8);
        lit.add(4);

       List<Integer> list2 = lit.stream().sorted().collect(Collectors.toList());
       Integer sum = lit.stream().reduce(Integer::sum).get();


       List<Estudante> listEstudante = com.sun.tools.javac.util.List.of(
       new Estudante(1, 18, "Patricia"), new Estudante(2, 21, "Eduardo"),
       new Estudante(3, 15, "Jose"));

/**
 *
 * peek é um metodo intermedio que permite conferir o fluxo da stream, tambem podemos fazer modificações no objetos
 */
        listEstudante.stream().peek(e -> {
            if(e.total == 0) {
                e.total = 5;
            }
        }).forEach(System.out::println);

       System.out.println(sum);

       list2.forEach(System.out::println);



        /**
         *map: permite transforma a lista em uma lista nova
         * */
       List<Estudante> listaNova =  listEstudante.stream().map(l -> new Estudante(l.id, l.idade, l.nome)).collect(Collectors.toList());

       /**
        * lo normal es retornar un Stream
        * en este caso retornamos un Stream de enteros
        * */
       Stream<Integer> listaNovaString =  listEstudante.stream().map(l -> l.idade);

       listaNova.forEach(System.out::println);
        listaNovaString.forEach(System.out::println);



        /**
         * Sorted: retornara uma nova lista ordenada, recibiendo una funcion comparador
         * en este caso fue utilizado el hashcode da clase estudiante y el metdodo comparing de la interface Comparator
         * */
        listEstudante.stream().sorted(Comparator.comparing(Estudante::hashCode)).forEach(System.out::println);


        /**
         * Distict: retira del stream valores repetidos
         * en este caso el distict tomo como referencia el hashcode do objeto
         * */
        listEstudante.stream().distinct().forEach(System.out::println);



        /**
         * Limit: es utilizado para limitar la cantidad de elementos en una stream
         *
         * en este caso limitamos la cantidad de stream para retornar el primero
         * */
        listEstudante.stream().limit(1).forEach(System.out::println);


        //// Operaciones terminales
        /***
         *  average: nos entrega la media de todos los valores
         *
         *  en este caso utilizamos el mapToInt nos retornara todos los valores enteros e sera obrigado definirlo de esa manera para poder utilizar AVERAGE
         */

        System.out.println(listEstudante.stream().mapToInt(l -> l.idade).average().getAsDouble());

        /**
         * count: el nos retorna la cantidad total del stream
         * */
        System.out.println(listEstudante.stream().filter(l -> l.idade >= 18).count());

        /**
         * AllMatch: aceita um predicate, y retorna un boolean, esta operacion valida si todos los elementos de el stream atienden el criterio
         * */
        boolean isAllNull = listEstudante.stream().allMatch(l -> l.idade > 18);
        System.out.println(isAllNull);



    }





}



class Estudante {

    Integer id;
    Integer idade;
    String nome;
    int total;

    public Estudante(Integer id, Integer idade, String nome) {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "id=" + id +
                ", idade=" + idade +
                ", nome='" + nome + '\'' +
                ", total=" + total +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudante estudante = (Estudante) o;
        return Objects.equals(id, estudante.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}


