/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Administrator
 */
import java.util.ArrayList;
public class GCMixto {
    
    private int a; //constante multiplicativa
    private int c; //constante aditiva
    private int m; //modulo
    private int Xo; //semilla
    private int Xn; //resultado del generador en la iteracion n
    private double Un; //division de Xn/m para cada iteracion n
    private ArrayList<GCMixto> resultadoGenerado;
    private int periodo;
    private ArbolB arbol;
    public GCMixto(int a,int c, int m,int Xo){
        this.a=a;
        this.c=c;
        this.m=m;
        this.Xo=Xo;
        resultadoGenerado=new ArrayList<>();
        arbol=new ArbolB();
        calcularXn();
        this.periodo=-1;
    }
    public GCMixto(int m){
        this.m=m;
        c=m-1;
        a=m;
        resultadoGenerado=new ArrayList<>();
        //buscarGenerador();
    }
    public boolean esGeneradorCompleto(){
        boolean res=true;
        int tam=resultadoGenerado.size();
        while(tam!=m&&res){
            if(buscarGenerador()){
                limpiarLista();
                calcularXn();
                generarNumeros();
                tam=resultadoGenerado.size();
            }else{
                res=false;
            }
        }
        return res;
        
    }
    /**
     * busca valores para a,c tal que generen periodo completo en base a m
     */
    public boolean buscarGenerador(){
        boolean prop1,prop2,prop3;
        prop1=buscarCoprimo();
        prop2=false;
        prop3=false;
        if(prop1){
            while(!prop2&&!prop3&&a>2){
               a--;
               if(a%2==1){
                if(prop2()){
                    if(prop3()){
                        prop2=true;
                        prop3=true;
                        Xo=m/2;
                        //calcularXn();
                    }
                }    
               }
               
            }      
        }
        return prop1&&prop2&&prop3;
    }
    private boolean buscarCoprimo(){
        boolean res=false;
        for(int i=c;i>2&&!res;i--){
            c=i;
            if(c%2==1){
               res=prop1();
            }
        }
        
        return res;
    }
    private void limpiarLista(){
        resultadoGenerado.clear();
    }
    /**
     * es de periodo completo si cumple las 3 propiedades 
     */
    public boolean esPeriodoCompleto(){
        boolean res=false;
        if(prop1()&&prop2()&&prop3()){
            periodo=m;
            res=true;
        }
        return res;
    }
    public void calcularXn(){
        Xn=(a*Xo+c)%m;
        Un=(Xn+0.0)/m;
        resultadoGenerado.add(this);
    }
   
    /**
     * c y m deben ser primos entre si
     */
    private boolean prop1(){
        return MCD(c,m)==1? true:false;
    }
    /**
     * 
     *  
     */
    private boolean prop2(){
        boolean res=true;
        for(int i=2;i<=m/2&&res;i++){
            if(m%i==0&&esPrimo(i)){
                res=(a-1)%i==0;
            }
        }
        return res;
    }
    private boolean esPrimo(int n){
        boolean res=n!=1;
        for(int i=2;i<=n/2&&res;i++){
            res=n%i!=0;
        }
        return res;
    }
    private boolean prop3(){
        boolean res=true;
        if(m%4==0){
            res=(a-1)%4==0;
        }
        return res;
    }
    public int MCD(int a, int c){
        if(c==0){
            return a;
        }else{
            return MCD(c,a%c);
        }
    }
    public void generarNumeros(){
        if(resultadoGenerado.size()==1){ 
            int aux=Xo;
            GCMixto gcAux;
            for(int i=0;i<m;i++){
                gcAux=resultadoGenerado.get(i);
                if(gcAux.getXn()==Xo){
                    break;
                }
                resultadoGenerado.add(new GCMixto(a, c, m,gcAux.getXn()));

            }
        }
    }
     public void generarNumeros2(){
        if(resultadoGenerado.size()==1){ 
            int aux=Xo;
            GCMixto gcAux;
            for(int i=0;i<m;i++){
                gcAux=resultadoGenerado.get(i);
                if(!arbol.agregar(gcAux.getXn())){
                    break;
                }
                resultadoGenerado.add(new GCMixto(a, c, m,gcAux.getXn()));

            }
        }
    }
    public int getA() {
        return a;
    }

    public int getC() {
        return c;
    }

    public int getM() {
        return m;
    }

    public int getXo() {
        return Xo;
    }

    public int getXn() {
        return Xn;
    }

    public double getUn() {
        return Un;
    }

    public ArrayList<GCMixto> getResultadoGenerado() {
        return resultadoGenerado;
    }

    public int getPeriodo() {
        return periodo;
    }
    
    
}
