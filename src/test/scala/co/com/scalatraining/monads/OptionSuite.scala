package co.com.scalatraining.monads

import org.scalatest.FunSuite

class OptionSuite extends FunSuite {

  def max(o1:Option[Int], o2:Option[Int]):Option[Int]={
    o1.fold{o2}{x=> o2.fold{o1}{y=>Some(Math.max(x,y))}}
  }

  test("test de carlos1"){
    assert(max(Some(3),Some(6))==Some(6))
  }
  test("test de carlos2"){
    assert(max(Some(3),None)==Some(3))
  }
  test("test de carlos3"){
    assert(max(None,Some(6))==Some(6))
  }
  test("test de carlos4"){
    assert(max(None,None)==None)
  }

  test("Se debe poder crear un Option con valor"){
    val s = Option{
      1 + 2 + 3
    }
    assert(s == Some(6))
  }

  test("Se debe poder crear un Option para denotar que no hay valor"){
    val s = None
    assert(s == None)
  }

  test("Es inseguro acceder al valor de un Option con get"){
    val s = None
    assertThrows[NoSuchElementException]{
      val r = s.get
    }


  }

  test("Se debe poder hacer pattern match sobre un Option") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(1)
    var res = ""
    res = nombre match {
      case Some(nom) => nom
      case None => "NONAME"
    }
    assert(res == "NONAME")
  }

  test("Fold en Option"){
    val o = Option(1)

    val res: Int = o.fold{
      10
    }{
      x => x + 20
    }

    assert(res == 21)
  }

  test("Fold en Option2"){
    val o = Option(1)

    val res: String = o.fold{
      "10"
    }{
      x => "hola"
    }

    assert(res == "hola")
  }

  test("Se debe poder saber si un Option tiene valor con isDefined") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(0)
    assert(nombre.isDefined)
  }

  test("Se debe poder acceder al valor de un Option de forma segura con getOrElse") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(1)
    val res = nombre.getOrElse("NONAME")
    assert(res == "NONAME")
  }

  test("implementar getOrElse con fold") {
    val o = Option(1)
    val r1 = o.getOrElse(666)
    val r2 = o.fold{666}{x=>x}

    assert(r1==r2)
  }

  test("Un Option se debe poder transformar con un map") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(0)
    val nombreCompleto: Option[String] = nombre.map(s => s + " Felipe")
    assert(nombreCompleto.getOrElse("NONAME") == "Andres Felipe")
  }

  test("Un Option es inmutable") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(0)
    val nombreCompleto: Option[String] = nombre.map(s => s + " Felipe")
    assert(nombreCompleto.getOrElse("NONAME") == "Andres Felipe")
    assert(nombre.getOrElse("NONAME") != "Andres Felipe")
  }

  test("Un Option se debe poder transformar con flatMap en otro Option") {
    val lista = List(Some("Andres"), None, Some("Luis"), Some("Pedro"))
    val nombre = lista(0)

    val resultado = nombre.flatMap(s => Option(s.toUpperCase))
    resultado.map( s => assert( s == "ANDRES"))
  }

  test("Un Option se debe poder filtrar con una hof con filter") {
    val lista = List(Some(5), None, Some(40), Some(20))
    val option0 = lista(0)
    val option1 = lista(1)
    val res0 = option0.filter(_>10)
    val res1 = option1.filter(_>10)

    assert(res0 == None)
    assert(res1 == None)
  }

  test("Un Option se debe poder filtrar con una hof con filter2") {
    val lista = List(Some(5), None, Some(40), Some(20))
    val option0 = lista(0)
    val option1 = lista(1)
    val res0 = option0.filter(_>1)

    assert(res0 == Some(5))
  }

  test("for comprehensions en Option") {
    val lista = List(Some(5), None, Some(40), Some(20))
    val s1 = lista(0)
    val s2 = lista(2)
    val resultado = for {
      x <- s1
      y <- s2
    } yield x+y
    assert(resultado == Some(45))
  }

  test("for comprehesions None en Option") {
    val consultarNombre = Some("Andres")
    val consultarApellido = Some("Estrada")
    val consultarEdad = None
    val consultarSexo = Some("M")

    val resultado = for {
      nom <- consultarNombre
      ape <- consultarApellido
      eda <- consultarEdad
      sex <- consultarSexo
    //} yield (nom+","+ape+","+eda+","+sex)
    } yield (s"$nom,$ape,$eda,$sex")

    assert(resultado == None)
  }

  test("for comprehesions None en Option2") {

    def consultarNombre(dni:String): Option[String] = Some("Felix")
    def consultarApellido(dni:String): Option[String] = Some("Vergara")
    def consultarEdad(dni:String): Option[String] = None
    def consultarSexo(dni:String): Option[String] = Some("M")

    val dni = "8027133"
    val resultado = for {
      nom <- consultarNombre(dni)
      ape <- consultarApellido(dni)
      eda <- consultarEdad(dni)
      sex <- consultarSexo(dni)
    //} yield (nom+","+ape+","+eda+","+sex)
    } yield (s"$nom $ape, $eda,$sex")

    assert(resultado == None)
  }

  test("for comprehesions None en Option3") {

    def consultarNombre(dni:String): Option[String] = {
      println("ejecutando consultar Nombre")
      Some("Felix")
    }
    def consultarApellido(dni:String): Option[String] = {
      println("ejecutando consultar Apellido")
      Some("Vergara")
    }
    def consultarEdad(dni:String): Option[String] = {
      println("ejecutando consultar Edad")
      None
    }
    def consultarSexo(dni:String): Option[String] = {
      println("ejecutando consultar Sexo")
      Some("M")
    }

    val dni = "8027133"
    val resultado = for {
      nom <- consultarNombre(dni)
      ape <- consultarApellido(dni)
      eda <- consultarEdad(dni)
      sex <- consultarSexo(dni)
      //} yield (nom+","+ape+","+eda+","+sex)
    } yield (s"$nom $ape, $eda,$sex")

    assert(resultado == None)
  }
}

