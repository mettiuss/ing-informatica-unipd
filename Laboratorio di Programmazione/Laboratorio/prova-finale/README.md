
```cpp
class Tabellone {
 vector<Casella> caselle; //lunghezza fissa, 28
}


class Casella {
}

class CasellaAngolare: public Casella {
}

class CasellaPartenza: public CasellaAngolare{
}

class CasellaLaterale {
	Edificio edificio;
}

class CasellaEconomica: public CasellaLaterale {
}

class CasellaStandard: public CasellaLaterale {
}

class CasellaLusso: public CasellaLaterale {
}


class Edificio {
}

class Casa: public Edificio {
}

class Albergo: public Edificio {
}


class Giocatore {
	int denaro;
}

class GiocatoreUmano: public Giocatore {
}

class GiocatoreComputer: public Giocatore {
}


class Game { //quella grossa
	Tabellone tabellone;
	vector<Giocatore> giocatori;
}
```
