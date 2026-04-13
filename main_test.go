package main

import "testing"

func TestSoma(t *testing.T) {
	resultado := Soma(2, 3)
	esperado := 5
	if resultado != esperado {
		t.Errorf("Resultado esperado %d, mas obteve %d", esperado, resultado)
	}
}
