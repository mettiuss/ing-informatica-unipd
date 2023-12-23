```cpp
class Board {
    vector<Casella> caselle; //lunghezza fissa, 28
}


class Tile {
}

class CornerTile: public Tile {
}

class LateralTile: public Tile {
	Edificio edificio;
}

class Building {
}


class Player {
	int denaro;
    PlayerType type;
}

class Game { //quella grossa
	Board board;
	vector<Player> players;
}
```
