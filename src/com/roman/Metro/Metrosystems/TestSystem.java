package com.roman.Metro.Metrosystems;

import com.roman.Metro.Line;
import com.roman.Metro.MetroStation;

import java.awt.*;


public class TestSystem extends MetroSystem{

    private Line Red;
    private Line Green;
    private Line Blue;
    private Line Purple;
    private Line Orange;
    private Line Brown;
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
        Brown = new Line(new Color(101, 67, 33));
        Orange = new Line(Color.ORANGE);
        Red = new Line(new Color(227, 41, 2));
        Green = new Line(new Color(19, 172, 11));
        Blue = new Line(new Color(14, 4, 204));
        Purple = new Line(new Color(196, 6, 143));

        A = new MetroStation("A", -120, -80, Purple);
        B = new MetroStation("B", -120, -40, Purple);
        C = new MetroStation("C", -80, -40, Purple);
        D = new MetroStation("D", -80, -40, Green);
        E = new MetroStation("E", 0, 0, Brown);
        F = new MetroStation("F", 0, 0, Purple);
        G = new MetroStation("G", 80, 40,  Green);
        H = new MetroStation("H", 80, 40, Purple);
        I = new MetroStation("I", 120,40, Purple);
        J = new MetroStation("J", 0, -160, Orange);
        K = new MetroStation("K", 0, -120, Orange);
        L = new MetroStation("L", 0, -80, Orange);
        M = new MetroStation("M", 0, -80, Green);
        N = new MetroStation("N", 0, 0, Red);
        O = new MetroStation("O", 80, -40,  Green);
        P = new MetroStation("P", 80, -40, Red);
        Q = new MetroStation("Q", 120, -40, Red);
        R = new MetroStation("R", 120, -80, Red);
        S = new MetroStation("S", -80, 40, Blue);
        T = new MetroStation("T", -120, 40, Blue);
        U = new MetroStation("U", -120, 80, Blue);
        V = new MetroStation("V", 0, 80, Brown);
        W = new MetroStation("W", 0, 80, Green);
        X = new MetroStation("X", 0, 120, Brown);
        Y = new MetroStation("Y", 40, 120, Brown);
        Z = new MetroStation("Z", -80, 40, Green);
    }

    //Сначала что, потом с кем
    //в порядке, обратном афлавитному(от Z до A)
    protected void connection(){
        Reader.put(A, B);
        Reader.put(B, C, A);
        Reader.put(C, F, D, B);
        Reader.put(D, Z, M, C);
        Reader.put(E, V, N, F);
        Reader.put(F, N, H, E, C);
        Reader.put(G, W, O, H);
        Reader.put(H, I, G, F);
        Reader.put(I, H);
        Reader.put(J, K);
        Reader.put(K, L, J);
        Reader.put(L, M, K);
        Reader.put(M, O, L, D);
        Reader.put(N, P, F, E);
        Reader.put(O, P, M, G);
        Reader.put(P, Q, O, N);
        Reader.put(Q, R, P);
        Reader.put(R, Q);
        Reader.put(S, Z, T);
        Reader.put(T, U, S);
        Reader.put(U, T);
        Reader.put(V, X, W, E);
        Reader.put(W, Z, V, G);
        Reader.put(X, Y, V);
        Reader.put(Y, X);
        Reader.put(Z, W, S, D);
    }
}
