% Define system matrices
A = [-0.00643 0.0253 0 -32.174 0;
     -0.0941 -0.624 820.02 0 0;
     -0.0002021 -0.001398 -0.8418 0 0;
     0 0 1 0 0;
     0 -1 0 830 0];

B = [0; -32.7; -2.073; 0; 0];
C = [0 0 0 0 1];
D = 0;

% Create state-space system
sys = ss(A, B, C, D);

%PDF_IMPULSE Given a state space system saves a pdf of its step
%response
[y,t] = step(sys);


% --- Time-Domain Impulse Response ---
h = figure;
plot(t,  squeeze(y), 'LineWidth',2);
%fontsize(12,"points");
xlabel('Tempo (s)');
ylabel('Altitudine (z(t))')
grid on;

axis([0 4500 -120000000 0])

set(h,'Position', [0 0 500 300]);
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'output','-dpdf','-r0')