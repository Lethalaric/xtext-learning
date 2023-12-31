/**
 * generated by Xtext 2.31.0
 */
package org.example.domainmodel.tests;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.example.domainmodel.domainmodel.AbstractElement;
import org.example.domainmodel.domainmodel.Domainmodel;
import org.example.domainmodel.domainmodel.DomainmodelPackage;
import org.example.domainmodel.domainmodel.Entity;
import org.example.domainmodel.domainmodel.Feature;
import org.example.domainmodel.validation.DomainmodelValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InjectionExtension.class)
@InjectWith(DomainmodelInjectorProvider.class)
@SuppressWarnings("all")
public class DomainmodelParsingTest {
  @Inject
  private ParseHelper<Domainmodel> parseHelper;

  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Hello Xtext!");
      _builder.newLine();
      final Domainmodel result = this.parseHelper.parse(_builder);
      Assertions.assertNotNull(result);
      final EList<Resource.Diagnostic> errors = result.eResource().getErrors();
      boolean _isEmpty = errors.isEmpty();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Unexpected errors: ");
      String _join = IterableExtensions.join(errors, ", ");
      _builder_1.append(_join);
      Assertions.assertTrue(_isEmpty, _builder_1.toString());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void parseDomailModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity MyEntity {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("parent: MyEntity");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Domainmodel model = this.parseHelper.parse(_builder);
      AbstractElement _head = IterableExtensions.<AbstractElement>head(model.getElements());
      final Entity entity = ((Entity) _head);
      Assertions.assertSame(entity, IterableExtensions.<Feature>head(entity.getFeatures()).getType());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Inject
  private ValidationTestHelper validationTestHelper;

  @Test
  public void testValidModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity MyEntity {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("parent: MyEntity");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Domainmodel entity = this.parseHelper.parse(_builder);
      this.validationTestHelper.assertNoIssues(entity);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  @Test
  public void testNameStartsWithCapitalWarning() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("entity myEntity {");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("parent: myEntity");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final Domainmodel entity = this.parseHelper.parse(_builder);
      this.validationTestHelper.assertWarning(entity, 
        DomainmodelPackage.Literals.TYPE, 
        DomainmodelValidator.INVALID_NAME, 
        "Name should start with a capital");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
