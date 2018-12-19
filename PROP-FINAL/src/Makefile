compilar:
	javac -cp lib/hamcrest-core-1.3.jar:lib/junit-4.12.jar:. capaDatos/*.java capaDominio/*.java generadorhorario/*.java testDatos/*.java testDominio/*.java

run: generadorhorario/GeneradorHorario.class
	java generadorhorario.GeneradorHorario

testAsignatura: testDatos/AsignaturaTest.class
	java testDatos.AsignaturaTest

testAula: testDatos/AulaTest.class
	java testDatos.AulaTest
	
testGestionDatos: testDatos/GestionDatosTest.class
	java testDatos.GestionDatosTest

testMateria: testDatos/MateriaTest.class
	java testDatos.MateriaTest
	
testRequisito: testDatos/RequisitoTest.class
	java testDatos.RequisitoTest

testAsignacion: testDominio/AsignacionTest.class
	java testDominio.AsignacionTest

testFranjaHoraria: testDominio/FranjaHorariaTest.class
	java testDominio.FranjaHorariaTest

testRestricciones: testDominio/RestriccionesTest.class
	java testDominio.RestriccionesTest

testJunit: capaDominio/CjtAsignacionesTest.class
	java -cp ./lib/junit-4.12.jar:./lib/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore capaDominio.CjtAsignacionesTest
		
clean:
	rm capaDatos/*.class capaDominio/*.class generadorhorario/*.class testDatos/*.class testDominio/*.class
