package com.roman.Metro.Metrosystems;

import com.roman.Metro.Line;
import com.roman.Metro.MetroStation;

import java.awt.*;


public class TestSystem extends MetroSystem{

    public static final int NUMOFSTATIONS = 3;
    private Line Red;
    private Line Green;
    private Line Blue;
    private Line Purple;
    private MetroStation A;
    private MetroStation B;
    private MetroStation C;
    private MetroStation D;
    private MetroStation E;
    private MetroStation F;
    private MetroStation G;
    private MetroStation H;
    private MetroStation I;
    private MetroStation J;
    private MetroStation K;
    private MetroStation L;
    private MetroStation M;
    private MetroStation N;
    private MetroStation O;
    private MetroStation P;
    private MetroStation Q;
    private MetroStation R;
    private MetroStation S;
    private MetroStation T;
    private MetroStation U;
    private MetroStation V;
    private MetroStation W;
    private MetroStation X;
    private MetroStation Y;
    private MetroStation Z;



    public TestSystem(){ super("com/roman/Resource/TestMetro.txt"); }

    protected void stationsInit() {
        Red = new Line(new Color(227, 41, 2));
        Green = new Line(new Color(19, 172, 11));
        Blue = new Line(new Color(14, 4, 204));
        Purple = new Line(new Color(196, 6, 143));

        A = new MetroStation("A", 60, 60, Purple);
        B = new MetroStation("B", 60, 100, Purple);
        C = new MetroStation("C", 60, 100, Red);
        D = new MetroStation("D", 60, 100, Green);
        E = new MetroStation("E", 100, 100, Red);
        F = new MetroStation("F", 60, 140, Green);
        G = new MetroStation("G", 20, 140, Purple);
        H = new MetroStation("H", 20, 180, Purple);
        //Проверка других ситуаций

        I = new MetroStation("I", 60, 200, Red);
        J = new MetroStation("J", 100, 200, Blue);
        K = new MetroStation("K", 100, 240, Red);
        L = new MetroStation("L", 100, 240, Blue);
        M = new MetroStation("M", 100, 240, Green);
        N = new MetroStation("N", 140, 280, Green);
        O = new MetroStation("O", 60, 280, Red);
        P = new MetroStation("P", 100, 280, Blue);
        Q = new MetroStation("Q", 140, 200, Green);
        R = new MetroStation("R", 180, 200, Green);
        /*
        S = new MetroStation("S", 20, 60, Color.CYAN);
        T = new MetroStation("T", 20, 60, Color.CYAN);
        U = new MetroStation("U", 20, 60, Color.CYAN);
        V = new MetroStation("V", 20, 60, new Color(69, 50, 46));
        W = new MetroStation("W", 20, 60, Color.GREEN);
        X = new MetroStation("X", 20, 60, new Color(69, 50, 46));
        Y = new MetroStation("Y", 20, 60, new Color(69, 50, 46));
        Z = new MetroStation("Z", 20, 60, Color.GREEN);*/
        connection();
    }

    //Сначала что, потом с кем
    //в порядке, обратном афлавитному(от Z до A)
    private void connection(){
        Reader.put(A, B);
        Reader.put(B, G, D, C, A);
        Reader.put(C, E, D, B);
        Reader.put(D, F, C, B);
        Reader.put(E, C);
        Reader.put(F, D);
        Reader.put(G, H, B);
        Reader.put(H, G);

        Reader.put(I, K);
        Reader.put(J, L);
        Reader.put(K, O, L, I);
        Reader.put(L, P, M, K, J);
        Reader.put(M, Q, N, L, K);
        Reader.put(N, M);
        Reader.put(O, K);
        Reader.put(P, L);
        Reader.put(Q, R, M);
        Reader.put(R, Q);
    }
}
