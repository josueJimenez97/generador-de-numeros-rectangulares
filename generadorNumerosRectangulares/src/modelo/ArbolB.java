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
public class ArbolB {
    private int raiz;
    private ArbolB izq,der;
    public ArbolB(){
        izq=null;
        der=null;
    }
    public boolean isEmpty(){
        return izq==null&&der==null;
    }
    public boolean agregar(int v){
        boolean res;
        if(isEmpty()){
         raiz=v;
         izq=new ArbolB();
         der=new ArbolB();
         res=true;
        }else if(v==raiz){
                res=false;
        }else if(v<raiz){
                res=izq.agregar(v);
        }else{
                res=der.agregar(v);
        }
        return res;
        
        
    }
    
}
