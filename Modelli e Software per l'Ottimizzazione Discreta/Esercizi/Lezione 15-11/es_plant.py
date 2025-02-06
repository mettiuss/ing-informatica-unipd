from pyscipopt import Model

tenute = ["A", "B", "C"]
dimensioni = [600, 700, 500]
acqua = [8000, 5500, 6000]

culture = ["mais", "grano", "orzo"]
profitto = [6500, 5000, 6000]
consumo = [16, 12, 14]


def build_model():
    model = Model()

    # Di default queste variabili sono continue e non negative
    p_stn = model.addVar("p_stn")
    p_lux = model.addVar("p_lux")

    # Funzione Obiettivo
    model.setObjective(300 * p_stn + 1400 * p_lux, sense="maximize")

    model.addCons(p_stn * 2 + p_lux * 4 <= 20 * 40)
    model.addCons(p_stn * 3 + p_lux * 5 <= 30 * 40)
    model.addCons(p_stn <= 500)
    model.addCons(p_stn >= 0)
    model.addCons(p_stn <= 500)
    model.addCons(p_lux >= 0)
    model.addCons(p_lux <= 1 / 2 * (p_lux + p_stn))

    model.data = p_stn, p_lux

    return model


if __name__ == "__main__":
    model = build_model()
    model.optimize()
    print("Optimal value: ", model.getObjVal())
    p_stn, p_lux = model.data
    print("p_stn: ", model.getVal(p_stn))
    print("p_lux: ", model.getVal(p_lux))
