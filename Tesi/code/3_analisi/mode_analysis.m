% short-period
%sys = tf(1,[1 1.46606 1.67433]);

% phugoid
sys = tf(1,[1 0.0061454 0.00010455859313 0]);

%spiral
% sys= tf(1, [1 0.0073]);

%roll
%sys= tf(1, [1 0.5630]);

% dutch roll
%sys = tf(1, [1 0.0656 0.89940068]);

damp(sys)
[y,t] = impulse(sys);


% --- Time-Domain Impulse Response ---
h = figure;
plot(t,  squeeze(y), 'LineWidth',2);
%fontsize(12,"points");
xlabel('Tempo (s)');
ylabel('r (rad/s)')
grid on;

set(h,'Position', [0 0 500 200]);
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'mode','-dpdf','-r0')