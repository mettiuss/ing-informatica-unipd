#ifndef BUILDING
#define BUILDING

class Building {
 public:
  enum BuildingType { House, Hotel };
  Building(BuildingType t = House) : type{t} {};
  BuildingType getType() { return type; };

 private:
  BuildingType type;
};

#endif
