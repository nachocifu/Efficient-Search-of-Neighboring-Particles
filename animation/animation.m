function animation(static_file, dynamic_file, output_file, M, particle_id)
  % Static File
  fid = fopen(static_file,'r');
  N = str2num(fgetl(fid));
  L = str2num(fgetl(fid));
  fclose(fid);
  [radius colour] = textread(static_file,"%f %f", 'headerlines', 2);
  % Dynamic File
  [x y] = textread(dynamic_file,"%f %f", 'headerlines', 1);
  fid = fopen(output_file,'r');
  fgetl(fid); % Omit header line
  neighbour={};
  for i = 1:N
    line = fgetl(fid);
    neighbour{end+1} = textscan(line,'%d'); % append element
  end

  plot = scatter(x, y, 'b', 'filled');
  title('Cell Index Method');
  xlabel('X position');
  ylabel('Y position');
  hold on;

  particleX = x(particle_id);
  particleY = y(particle_id);
  plot = scatter(particleX, particleY, 'g', 'filled');
  highlighted = neighbour{particle_id}{1}(2:end);
  highlightedX = x(highlighted);
  highlightedY = y(highlighted);

  plot = scatter(highlightedX, highlightedY,'r', 'filled');
  set(gca,'xtick',[0:(L/M):L]);
  set(gca,'ytick',[0:(L/M):L]);
  grid on;
endfunction