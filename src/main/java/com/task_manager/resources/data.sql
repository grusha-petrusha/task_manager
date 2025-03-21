INSERT INTO users(email, password, role) VALUES
('admin@example.com', 'hashed_password', 'ADMIN'),
('user@example.com', 'hashed_password', 'USER');

INSERT INTO tasks (title, description, priority, status, author_id, executor_id) VALUES
('Test 1', '1111', 'HIGH', 'IN_PROGRESS', 1, 2),
('TEst 2', '1111', 'LOW', 'PENDING', 1, 2);