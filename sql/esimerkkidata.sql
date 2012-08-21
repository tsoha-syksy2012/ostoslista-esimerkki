INSERT INTO users (username, password) VALUES
  ('tester', 'password123'),
  ('lester', 'password321');

INSERT INTO lists (user_id, name, is_default) VALUES
  ((SELECT id FROM users WHERE username = 'tester'), 'Kauppaan', true),
  ((SELECT id FROM users WHERE username = 'tester'), 'Remontti', false),
  ((SELECT id FROM users WHERE username = 'tester'), 'Lahjoja siskolle', false),
  ((SELECT id FROM users WHERE username = 'lester'), 'Ruokaa', true),
  ((SELECT id FROM users WHERE username = 'lester'), 'Viinaa', false);

INSERT INTO items (list_id, name) VALUES
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Kauppaan'), 'Leipää'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Kauppaan'), 'Maitoa'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Kauppaan'), 'Juustoa'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Kauppaan'), 'WC-paperia'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Remontti'), 'Nauloja'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Remontti'), 'Vasara'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Remontti'), 'Pora'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Remontti'), 'Olutta'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Lahjoja siskolle'), 'Teddy-karhu'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'tester') AND name = 'Lahjoja siskolle'), 'Kortti'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'lester') AND name = 'Ruokaa'), 'Leipää'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'lester') AND name = 'Ruokaa'), 'Piimää'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'lester') AND name = 'Ruokaa'), 'Pizzaa'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'lester') AND name = 'Viinaa'), 'Kossua'),
  ((SELECT id FROM lists WHERE user_id = (SELECT id FROM users WHERE username = 'lester') AND name = 'Viinaa'), 'Salmaria');
