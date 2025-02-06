from pyscipopt import Model


def build_model():
    model = Model()

    # Di default queste variabili sono continue e non negative
    gas = model.addVar("gas")
    chloride = model.addVar("chloride")

    # Funzione Obiettivo
    model.setObjective(40 * gas + 50 * chloride, sense="maximize")

    model.addCons(gas + chloride <= 50)
    model.addCons(3 * gas + 4 * chloride <= 100)
    model.addCons(chloride <= 40)

    model.data = gas, chloride

    return model


if __name__ == "__main__":
    model = build_model()
    model.writeProblem("es_base.lp")
    model.optimize()
    print("Optimal value: ", model.getObjVal())
    gas, chloride = model.data
    print("Gas: ", model.getVal(gas))
    print("Chloride: ", model.getVal(chloride))
