#include <iostream>

class Link {
 public:
  std::string value;  // public: sono solo dati
  Link(const std::string& v, Link* p = nullptr, Link* s = nullptr)
      : value{v}, prev{p}, succ{s} {}
  void insert(Link* n);  // inserimento prima di questo
  void add(Link* n);     // inserimento dopo questo
  Link* erase();         // rimuove questo
  Link* find(const std::string& s);
  const Link* find(const std::string& s) const;
  Link* advance(int n) const;
  Link* next() const { return succ; }
  Link* previous() const { return prev; }

 private:
  Link* prev;
  Link* succ;
};