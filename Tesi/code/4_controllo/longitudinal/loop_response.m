% Define state-space matrices
A = [-0.00643 0.0253 0 -32.174 0;
     -0.0941 -0.624 820.02 0 0;
     -0.0002021 -0.001398 -0.8418 0 0;
     0 0 1 0 0;
     0 -1 0 830 0];

B = [0; -32.7; -2.073; 0; 0];

C_q = [0 0 1 0 0];
C_h = [0 0 0 0 1];
D = 0;


% Define feedback gains
Kq = -1.73;      % gain for q (x3)
Ktheta = -6.1;   % gain for theta (x4)

% Create feedback matrix K such that:
K = [0 0 Kq Ktheta 0];

sys_cl = ss(A - B*K, B, C_h, D);

c = tf([-0.0082844, -0.00082844], 1);

sys_final = feedback(sys_cl*c, 1);

[y,t] = step(sys_final*100);
%[y,t] = impulse(sys_final);


% --- Time-Domain Impulse Response ---
h = figure;
plot(t,  squeeze(y), 'LineWidth',2);
%fontsize(12,"points");
xlabel('Tempo (s)');
ylabel('Altitudine (z(t))')
grid on;

xlim([0, 80])
%xlim([0, 10])

set(h,'Position', [0 0 500 200]);
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'output','-dpdf','-r0')