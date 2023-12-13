#include "Link.h"

#include <iostream>

void Link::insert(Link* n) {
  if (!n) return;
  n->prev = prev;
  n->succ = this;
  if (prev) prev->succ = n;
  prev = n;
}

void Link::add(Link* n) {
  if (!n) return;
  n->prev = this;
  n->succ = succ;
  if (succ) succ->prev = n;
  succ = n;
}

Link* Link::erase() {
  if (prev) prev->succ = succ;
  if (succ) succ->prev = prev;
  return this;
}

Link* Link::find(const std::string& s) {
  Link* p = new Link(this->value, this->prev, this->succ);
  while (p) {
    if (p->value == s) return p;
    p = p->succ;  // anziché ++p
  }
  p = new Link(this->value, this->prev, this->succ);
  while (p) {
    if (p->value == s) return p;
    p = p->prev;  // anziché ++p
  }
  delete p;
  return nullptr;
}

const Link* Link::find(const std::string& s) const {
  Link* p = new Link(this->value, this->prev, this->succ);
  while (p) {
    if (p->value == s) return p;
    p = p->succ;  // anziché ++p
  }
  p = new Link(this->value, this->prev, this->succ);
  while (p) {
    if (p->value == s) return p;
    p = p->prev;  // anziché ++p
  }
  delete p;
  return nullptr;
}

Link* Link::advance(int n) const {
  Link* p = new Link(this->value, this->prev, this->succ);
  for (int i = 0; i < std::abs(n); i++) {
    if (n > 0) {
      if (!p->succ) return p;
      p = p->succ;
    } else {
      if (!p->prev) return p;
      p = p->prev;
    }
  }
  return p;
}