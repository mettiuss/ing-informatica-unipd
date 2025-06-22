%longitudinale
A = [-0.00643 0.0253 0 -32.174 0;
    -0.0941 -0.624 820.02 0 0;
    -0.0002021 -0.001398 -0.8418 0 0;
    0 0 1 0 0;
    0 -1 0 830 0];
B = [0; -32.7; -2.073; 0; 0];
C = [0 0 0 0 1];
d = 0;


%laterale
%{
A = [-0.0558 -1 0.0802 0.0416;
    0.598 -0.115 -0.0318 0;
    -3.05 0.388 -0.465 0;
    0 0.0805 1 0];
B = [0.00729; -0.475; 0.153; 0];
C = [0 1 0 0];
d = 0;
%}
[num, den] = ss2tf(A, B, C, d)


poles = roots(den)
zeros_tf = roots(num)

h = figure;

plotoptions = pzoptions;
plotoptions.Grid = 'on';
plotoptions.GridColor = [0.25, 0.25, 0.25];
plotoptions.Title.String = '';
plotoptions.XLabel.String = 'Asse Reale';
plotoptions.XLabel.FontSize = 12;
plotoptions.YLabel.String = 'Asse Immaginario';
plotoptions.YLabel.FontSize = 12;
p = pzplot(ss(A, B, C, d), plotoptions);
p.Responses.MarkerSize = 10;
p.Responses.LineWidth = 1;
set(h,'Units','Inches');
pos = get(h,'Position');
set(h,'PaperPositionMode','Auto','PaperUnits','Inches','PaperSize',[pos(3), pos(4)])
print(h,'mode','-dpdf','-r0')
