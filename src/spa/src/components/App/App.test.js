import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Student Project text', () => {
  render(<App />);
  const linkElement = screen.getByText(/Student Project/i);
  expect(linkElement).toBeInTheDocument();
});
