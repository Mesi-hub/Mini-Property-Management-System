import { render, screen } from '@testing-library/react';
import App from '.';

test('renders Student Project text', () => {
  render(<App />);
  const linkElement = screen.getByText(/Student Project/i);
  expect(linkElement).toBeInTheDocument();
});
