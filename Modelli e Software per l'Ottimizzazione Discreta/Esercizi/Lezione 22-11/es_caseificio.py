from pyscipopt import Model, quicksum

prodotti = ["burro", "ricotta", "mozzarella"]
domanda = {"burro": 230, "ricotta": 150, "mozzarella": 140}
ricavo = {"burro": 3, "ricotta": 5, "mozzarella": 8}
quota = {"burro": 15, "ricotta": 6, "mozzarella": 11}
costo = {"burro": 1.5, "ricotta": 1.1, "mozzarella": 2}

costo_latte = 0.5
ore_disponibili = 200
costo_max = 900


def build_model():
    # Model
    model = Model()
    # variables
    x = {}
    for p in prodotti:
        x[p] = model.addVar(name="x[%s]" % p)
    # objective
    model.setObjective(quicksum((ricavo[p] - costo[p] * costo_latte) * x[p] for p in prodotti), sense="maximize")
    # constraints
    for p in prodotti:
        model.addCons(x[p] >= domanda[p])

    model.addCons(quicksum(x[p] / quota[p] for p in prodotti) <= ore_disponibili)
    model.addCons(quicksum(x[p] * costo[p] for p in prodotti) <= costo_max)
    model.data = x
    return model


if __name__ == "__main__":
    model = build_model()
    model.optimize()
    print("Optimal value: ", model.getObjVal())
    x = model.data
    for p in prodotti:
        print("{} = {}".format(p, model.getVal(x[p])))

# CORRETTTO
# Optimal value:  2610.0
# burro = 230.0
# ricotta = 250.0
# mozzarella = 140.0
