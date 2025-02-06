from pyscipopt import Model, quicksum

bicchieri = [1, 2, 3]
ricavo = {1: 28, 2: 35, 3: 32}
costo = {1: 800, 2: 900, 3: 700}

macchine = ["A", "B", "C", "U"]
max_macchine = {"A": 320, "B": 240, "C": 200, "U": 400}

tempo = {1: {"A": 3, "B": 1, "C": 2, "U": 4}, 2: {"A": 5, "B": 3, "C": 4, "U": 4}, 3: {"A": 2, "B": 4, "C": 3, "U": 4}}


def build_model():
    # Model
    model = Model()
    # variables
    x = {}
    for p in bicchieri:
        x[p] = model.addVar(name="x[%s]" % p)
        x[str(p) + "active"] = model.addVar(name="x[%s]" % p, vtype="B")
    # objective
    model.setObjective(
        quicksum((ricavo[p] * x[p] - costo[p] * x[str(p) + "active"]) for p in bicchieri), sense="maximize"
    )
    # constraints
    for m in macchine:
        model.addCons(quicksum(x[p] * tempo[p][m] for p in bicchieri) <= max_macchine[m])

    for p in bicchieri:
        model.addCons(x[p] <= 100000 * x[str(p) + "active"])
        model.addCons(x[str(p) + "active"] <= x[p])
    model.data = x
    return model


if __name__ == "__main__":
    model = build_model()
    model.optimize()
    print("Optimal value: ", model.getObjVal())
    x = model.data
    for p in bicchieri:
        print("{} = {}".format(p, model.getVal(x[p])))
        print("{} = {}".format(str(p) + "active", model.getVal(x[str(p) + "active"])))

# CORRETTTO
# Optimal value:  2000.0
# 1 = 100.0
# 1active = 1.0
# 2 = 0.0
# 2active = 0.0
# 3 = 0.0
# 3active = 0.0
