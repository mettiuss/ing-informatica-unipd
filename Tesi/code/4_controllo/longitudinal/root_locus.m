% Define state-space matrices
A = [-0.00643 0.0253 0 -32.174 0;
     -0.0941 -0.624 820.02 0 0;
     -0.0002021 -0.001398 -0.8418 0 0;
     0 0 1 0 0;
     0 -1 0 830 0];

B = [0; -32.7; -2.073; 0; 0];

C_q = [0 0 1 0 0];
C_theta = [0 0 0 1 0];
C_h = [0 0 0 0 1];
D = 0;

sys = ss(A, B, C_q, D);
%sys = ss(A, B, C_theta, D);

h = figure;

options = pzoptions;
options.Grid = 'on';
options.GridColor = [0.25, 0.25, 0.25];
options.Title.String = '';
options.XLabel.String = 'Asse Reale';
options.XLabel.FontSize = 12;
options.YLabel.String = 'Asse Immaginario';
options.YLabel.FontSize = 12;
p = rlocusplot(sys, -100:0.001:0, options);

p.Responses.MarkerSize = 10;
p.Responses.LineWidth = 1;

hold on;

CL_poles = rlocus(sys, -0.0533706);

% Plot these poles on the root locus
plot(real(CL_poles), imag(CL_poles), '*', 'MarkerSize', 6, 'LineWidth', 4);

set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'mode','-dpdf','-r0')
