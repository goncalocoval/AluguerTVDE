
# AluguerTVDE - Aluguer de carros destinados a TVDE

Este projeto realizado em Java consiste num programa destinado ao aluguer de carros, de modo a que estes possam ser utilizados em atividades TVDE, como serviços Uber. Tinha assim como objetivo demonstrar todo o conhecimento obtido em Linguagens e Paradigmas de Programação, principalmente de Programação Orientada a Objetos e seus conceitos.

Para além de existirem categorias no que diz respeito aos veículos, nomeadamente "Económico", "XL", "Executívo" e "Acessibilidade", contando com diferentes características (ou atributos), existe também uma série de normas que devem ser cumpridas, tanto no registo dos veículos no programa como dos clientes.

Para os veículos, e segundo as regras de TVDE em Portugal:
- Não podem ter mais do que 7 anos desde o ano atual
- Mínimo de 5 portas
- Capacidade máxima de 9 lugares
- Matrícula nacional
- Todos os documentos em ordem (seguro, inspeção, etc...)

Para os clientes:
- Precisam ter no mínimo 18 anos
- NIF português e válido
- Telemóvel português
- Data de validade da carta de condução superior à data atual

Validações do programa:
- NIFs iguais
- Número de cartas de condução iguais
- Matrículas iguais
- Se o veículo já se encontra alugado
- Se é possível alugar o veículo (documentos)
- Entre outras

Funcionalidades:
- Registo de automóveis para futuro aluguer
- Registo de clientes para alugarem veículos
- Criação e término de alugueis de veículos (com direito a recibo)
- Filtragem das diferentes categorias de carros
- Possibilidades de ver detalhes de alugueis terminados
- Pesquisa de clientes (por NIF)
- Entre outras

Para testar o programa, faça git clone https://github.com/goncalocoval/AluguerTVDE.git ou faça download do código-fonte e inicie por uma IDE (IntelliJ IDEA recomendado) ou crie um programa java executável a partir do código.

Projeto realizado por Gonçalo Coval e Gonçalo Silva.
