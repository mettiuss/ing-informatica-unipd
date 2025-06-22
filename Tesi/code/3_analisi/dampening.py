import numpy as np
import matplotlib.pyplot as plt

import matplotlib

matplotlib.use("Agg")  # Use non-GUI backend
matplotlib.rcParams["pdf.use14corefonts"] = True  # Helps with font embedding
matplotlib.rcParams["pdf.fonttype"] = 42  # Use TrueType fonts
matplotlib.rcParams["ps.fonttype"] = 42
matplotlib.rcParams["figure.facecolor"] = "white"  # Force white background
plt.rcParams["font.family"] = "serif"
plt.rcParams["font.serif"] = ["Times New Roman"] + plt.rcParams["font.serif"]

# Constants
m = 1  # kg
k = 25  # N/m
x0 = 1  # Initial displacement
t = np.linspace(0, 2, 10000)  # Time vector

w_n = np.sqrt(k / m)  # Natural frequency

# Damping ratios
zetas = [0.1, 0.5, 1, 2.5]
colors = ["#1f77b4", "#ff7f0e", "#2ca02c", "#d62728"]  # Blue, Orange, Green, Red

# Plot
fig, ax = plt.subplots(figsize=(10, 6))

for i, Z in enumerate(zetas):
    lam = np.zeros(2, dtype="complex")
    lam[0] = w_n * (-Z + np.sqrt(Z**2 - 1, dtype="complex"))
    lam[1] = w_n * (-Z - np.sqrt(Z**2 - 1, dtype="complex"))

    x = x0 * (0.5 * np.exp(lam[0] * t) + 0.5 * np.exp(lam[1] * t))

    ax.plot(t, x.real, label=f"$\\zeta = {Z}$", color=colors[i], alpha=1.0)

# Finalize plot
ax.set_xlabel("t [s]", fontsize=18, alpha=1.0)
ax.set_ylabel("x [m]", fontsize=18, alpha=1.0)
ax.grid(True, alpha=1.0)
legend = ax.legend(prop={"size": 18}, framealpha=1.0)
legend.get_frame().set_alpha(1.0)
ax.patch.set_alpha(1.0)
fig.patch.set_alpha(1.0)
plt.tight_layout()
plt.savefig(
    "vibrazione_sistema.pdf",
    format="pdf",
    transparent=False,
    facecolor="white",
    edgecolor="white",
    bbox_inches="tight",
    metadata={"Title": "Vibration Plot", "Producer": "Matplotlib"},
)
plt.show()
