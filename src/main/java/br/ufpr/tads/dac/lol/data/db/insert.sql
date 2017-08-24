INSERT INTO Sequence (idSequence)
-- SQLite
-- SELECT 'seq_'||lower(name)||'dao' FROM sqlite_master WHERE type = 'table';
SELECT 'seq_'||lower(tablename)||'dao' FROM pg_catalog.pg_tables WHERE schemaname = 'public';
-- PostgreSQL
-- Gera reorder sequences
-- SELECT 
--  'UPDATE Sequence SET valor = (SELECT MAX(id'||lower(tablename)
--  ||' FROM '||lower(tablename)
--  ||' WHERE idsequence = ''seq_'||lower(tablename)||'dao'''
-- FROM 
--  pg_catalog.pg_tables 
-- WHERE schemaname = 'public';
