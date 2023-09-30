package com.desafio1.Desafio1.services.Fila;

import org.springframework.stereotype.Service;
@Service
public class Fila<T> {

    public No<T> refUserEntradaFila;
    public No<T> refUserSaidaFila;

    public Fila() {
        this.refUserEntradaFila = null;
        this.refUserSaidaFila = null;
    }

    public boolean isEmpty() {
        return refUserEntradaFila == null;
    }

    public No<T> updateUser(T user) {
        No<T> novoNo = new No<>(user);
        if (isEmpty()) {
            refUserEntradaFila = novoNo;
            refUserSaidaFila = novoNo;
        } else {
            novoNo.setRefNo(refUserSaidaFila);
            refUserSaidaFila = novoNo;
        }
        return novoNo;
    }

    public void enqueue(T user) {
        No novoNo = new No(user);
        novoNo.setRefNo(refUserEntradaFila);
        refUserEntradaFila = novoNo;
    }

    public void clear() {
        refUserEntradaFila = null;
    }

    public T first() {
        if (!this.isEmpty()) {
            No primeiroNo = refUserEntradaFila;
            while(true){ 
                if(primeiroNo.getRefNo() != null){
                    primeiroNo = primeiroNo.getRefNo(); 
                }else {
                    break;
                }
            }
            return (T) primeiroNo.getObject();
        }
        return null;
    }

    public void remove(T elementToRemove) {
        No<T> current = refUserEntradaFila;
        No<T> previous = null;
    
        while (current != null) {
            T currentObject = (T) current.getObject();
            if (currentObject.equals(elementToRemove)) {
                if (previous == null) {
                    refUserEntradaFila = current.getRefNo();
                } else {
                    previous.setRefNo(current.getRefNo());
                }
                return;
            }
            previous = current;
            current = current.getRefNo();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringRetorno = new StringBuilder();
        No<T> noAuxiliar = refUserEntradaFila;
    
        if (refUserEntradaFila != null) {
            while (noAuxiliar != null) {
                T user = (T) noAuxiliar.getObject();
                stringRetorno.append("User: ").append(user.toString()).append("\n");
                noAuxiliar = noAuxiliar.getRefNo();
            }
        } else {
            stringRetorno.append("null");
        }
    
        return stringRetorno.toString();
    }
}